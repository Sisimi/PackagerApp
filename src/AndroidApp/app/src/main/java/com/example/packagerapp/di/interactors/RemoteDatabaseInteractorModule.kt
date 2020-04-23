package com.example.packagerapp.di.interactors

import android.content.Context
import com.example.packagerapp.R
import com.example.packagerapp.interactors.APIs.IRemoteDatabaseAPI
import com.example.packagerapp.interactors.APIs.IRemoteDatabaseInteractor
import com.example.packagerapp.interactors.APIs.RemoteDatabaseInteractor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.*


@Module
class RemoteDatabaseInteractorModule constructor(appContext:Context) {

    private var appContext = appContext

    @Provides
    fun provideRemoteDatabaseAPI() : IRemoteDatabaseAPI{

        val (sslContextFactory, trustAllCerts) = getUnsafeSSLConfig()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain->
                val original = chain.request()

                val request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build()

                chain.proceed(request)
            }
            //.sslSocketFactory(sslContextFactory, trustAllCerts[0] as X509TrustManager)
           /* .certificatePinner(CertificatePinner.Builder()
                .add("packagerwebapi.com","sha256/UY/xad0shlsaXkupqh4hHfQd1dZxvtrSV0GNMsb2quE=")
                .build())*/
            .build()


        val remoteDatabaseAPIInstance: IRemoteDatabaseAPI by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.15:5000/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(IRemoteDatabaseAPI::class.java)
        }

        return remoteDatabaseAPIInstance
    }

    @Provides
    fun provideRemoteDatabaseInteractor(remoteDatabaseAPI : IRemoteDatabaseAPI) : IRemoteDatabaseInteractor
    {
        return RemoteDatabaseInteractor(remoteDatabaseAPI)
    }

    private fun getSSLConfig(): SSLSocketFactory? {

        var certificateFactory = CertificateFactory.getInstance("X.509")

        var certificate: Certificate? = null
        appContext?.getResources()?.openRawResource(R.raw.packager_web_api_cert)
            .use({
                    cert -> certificate = certificateFactory.generateCertificate(cert)
            })

        // Creating a KeyStore containing our trusted CAs
        val keyStoreType: String = KeyStore.getDefaultType()
        val keyStore: KeyStore = KeyStore.getInstance(keyStoreType)
        keyStore.load(null, null)
        keyStore.setCertificateEntry("ca", certificate)

        // Creating a TrustManager that trusts the CAs in our KeyStore.
        val tmfAlgorithm: String = TrustManagerFactory.getDefaultAlgorithm()
        val tmf: TrustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm)
        tmf.init(keyStore)

        // Creating an SSLSocketFactory that uses our TrustManager
        val sslContext: SSLContext = SSLContext.getInstance("TLS")
        sslContext.init(null, tmf.getTrustManagers(), null)
        return sslContext.socketFactory
    }

    private fun getUnsafeSSLConfig() : Pair<SSLSocketFactory?, Array<TrustManager>> {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                        return arrayOf()
                    }
                }
            )

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            return Pair(sslContext.socketFactory, trustAllCerts)
    }

}