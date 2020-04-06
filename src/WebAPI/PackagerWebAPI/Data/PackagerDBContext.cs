using MongoDB.Driver;
using PackagerWebAPI.Misc;
using PackagerWebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PackagerWebAPI.Data
{
    public class PackagerDBContext
    {
        public IMongoCollection<Package> Packages { get; set; }
        
        public PackagerDBContext(IPackagerDBSettings packagerDBSettings)
        {
            var client = new MongoClient(packagerDBSettings.ConnectionString);
            var database = client.GetDatabase(packagerDBSettings.DatabaseName);

            Packages = database.GetCollection<Package>(packagerDBSettings.CollectionName);
        }
    }
}
