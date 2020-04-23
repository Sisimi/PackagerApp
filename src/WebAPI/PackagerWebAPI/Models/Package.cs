using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using MongoDB.Bson.Serialization.IdGenerators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PackagerWebAPI.Models
{
    public class Package
    {
        public Package()
        {

        }

        [BsonId(IdGenerator = typeof(StringObjectIdGenerator))]
        //[BsonRepresentation(BsonType.Decimal128)]
        public string Id { get; set; }

        public string PackageName { get; set; }

        public string Description { get; set; }

        public List<NameValue> NameValueList { get; set; }
    }
}
