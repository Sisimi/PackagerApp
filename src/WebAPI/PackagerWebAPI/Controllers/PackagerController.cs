using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using PackagerWebAPI.Models;
using PackagerWebAPI.Services;

namespace PackagerWebAPI.Controllers
{
    [Route("api/")]
    [ApiController]
    public class PackagerController : ControllerBase
    {
        private readonly IPackagerDBRepository packagerDBRepository;

        public PackagerController(IPackagerDBRepository packagerDBRepository)
        {
            this.packagerDBRepository = packagerDBRepository;
        }

        [HttpGet]
        public ActionResult<string> HealthCheck()
        {
            return "PackagerAPI is Working!";
        }
        
        [HttpGet]
        [Route("packages")]
        public async Task<ActionResult<IEnumerable<Package>>> GetAllPackages()
        {
            return await packagerDBRepository.GetPackages();
        }

        [HttpGet]
        [Route("package/{searchString}")]
        public async Task<ActionResult<IEnumerable<Package>>> GetSpecificPackages(string searchString)
        {
            return await packagerDBRepository.GetSpecificPackages(searchString);
        }

        [HttpPut]
        [Route("package/add")]
        public async Task<ActionResult<Package>> PutOrUpdatePackage(Package package)
        {
            return await packagerDBRepository.AddPackage(package);
        }

        [HttpDelete]
        [Route("package/delete/{id}")]
        public async Task<ActionResult<Package>> DeletePackage(string id)
        {
            throw new NotImplementedException();
        }

    }
}
