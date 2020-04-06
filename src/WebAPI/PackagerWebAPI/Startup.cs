using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;
using PackagerWebAPI.Data;
using PackagerWebAPI.Misc;
using PackagerWebAPI.Services;

namespace PackagerWebAPI
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddMvc().SetCompatibilityVersion(CompatibilityVersion.Version_2_2);

            services.Configure<PackagerDBSettings>(
       Configuration.GetSection(nameof(PackagerDBSettings)));

            services.AddSingleton<IPackagerDBSettings>(sp =>
                sp.GetRequiredService<IOptions<PackagerDBSettings>>().Value);

            services.AddSwaggerDocument(config =>
            {
                config.PostProcess = document =>
                {
                    document.Info.Title = "Packager App backend API";
                    document.Info.Description = "Alábbiakban látható a Packager App-hoz használt adatbázis kezelő API leírása.";
                    document.Info.Contact = new NSwag.OpenApiContact
                    {
                        Name = "Batta Tamás",
                        Email = "tomi9994@gmail.com"
                    };
                };
            });


            services.AddSingleton<PackagerDBContext>();
            services.AddSingleton<IPackagerDBRepository, PackagerDBRepository>();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                app.UseHsts();
            }

            app.UseHttpsRedirection();

            app.UseOpenApi();
            app.UseSwaggerUi3();

            app.UseMvc();
        }
    }
}
