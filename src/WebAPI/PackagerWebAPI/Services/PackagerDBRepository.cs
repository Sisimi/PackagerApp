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

        public async Task<Package> AddPackage(Package package)
        {
            await packagerDBContext.Packages.InsertOneAsync(package);
            return package;
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
