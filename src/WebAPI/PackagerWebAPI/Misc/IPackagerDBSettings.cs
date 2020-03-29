using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PackagerWebAPI.Misc
{
    public interface IPackagerDBSettings
    {
        string CollectionName { get; set; }
        string ConnectionString { get; set; }
        string DatabaseName { get; set; }
    }
}
