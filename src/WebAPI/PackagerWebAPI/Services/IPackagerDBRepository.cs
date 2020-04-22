using Microsoft.AspNetCore.Mvc;
using PackagerWebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PackagerWebAPI.Services
{
    public interface IPackagerDBRepository
    {
        Task<Package> AddPackage(Package package);
        Task<List<Package>> GetPackages();
        Task<List<Package>> GetSpecificPackages(string searchString);
        Task<Package> DeletePackage(string id);
        Task AddManyPackage(List<Package> packages);
    }
}
