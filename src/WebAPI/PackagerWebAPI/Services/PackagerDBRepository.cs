using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MongoDB.Driver;
using PackagerWebAPI.Data;
using PackagerWebAPI.Models;

namespace PackagerWebAPI.Services
{
    public class PackagerDBRepository : IPackagerDBRepository
    {
        private readonly PackagerDBContext packagerDBContext;

        public PackagerDBRepository(PackagerDBContext packagerDBContext)
        {
            this.packagerDBContext = packagerDBContext;
        }

        public async Task AddManyPackage(List<Package> packages)
        {
            var notSavedInstances = new List<Package>();
            foreach (var package in packages)
            {
                var result = await (await packagerDBContext.Packages.FindAsync(dbPackage => dbPackage.Id == package.Id)).ToListAsync();

                if (result.Count <= 0)
                {
                    notSavedInstances.Add(package);
                }
            } 

            if(notSavedInstances.Count>0)
            {
                await packagerDBContext.Packages.InsertManyAsync(notSavedInstances);
            }
        }

        public async Task<Package> AddPackage(Package package)
        {
            await packagerDBContext.Packages.InsertOneAsync(package);
            return package;
        }

        public async Task<Package> DeletePackage(string id)
        {
            var deletedPackage = await packagerDBContext.Packages.FindOneAndDeleteAsync(package => package.Id == id);
            return deletedPackage;
        }

        public async Task<List<Package>> GetPackages()
        {
            var rawList = await packagerDBContext.Packages.FindAsync(Builders<Package>.Filter.Empty);
            return await rawList.ToListAsync();
        }

        public async Task<List<Package>> GetSpecificPackages(string searchString)
        {
            var rawList = await packagerDBContext.Packages.FindAsync(
                package => package.PackageName.ToLower().Contains(searchString));
            return await rawList.ToListAsync();
        }
    }
}
