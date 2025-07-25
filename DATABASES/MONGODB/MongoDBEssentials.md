# 📘 MongoDB

## 🧩 What is MongoDB? (NoSQL, Document Database)
- MongoDB is a NoSQL, document-oriented database.
- Stores data in flexible, JSON-like documents (called BSON under the hood).
- Unlike relational databases (which use rows and tables), MongoDB uses:
    - Databases → contain Collections
    - Collections → contain Documents
- Key Features:
    - Schema-less (you can store different structures in the same collection)
    - High scalability & availability (great for modern web/mobile apps)
    - Built-in replication and sharding support
    - Designed for handling large volumes of unstructured/semi-structured data

**⚖️ MongoDB vs Relational Databases (MySQL, PostgreSQL)**
| Feature        | MongoDB                    | Relational DB (MySQL, PostgreSQL) |
| -------------- | -------------------------- | --------------------------------- |
| Data Structure | JSON-like Documents (BSON) | Tables with Rows and Columns      |
| Schema         | Flexible                   | Strict / Pre-defined              |
| Joins          | Limited, uses `$lookup`    | Supported                         |
| Transactions   | Supports (as of v4.0+)     | Supported                         |
| Query Language | MongoDB Query Language     | SQL                               |
| Scalability    | Horizontal (Sharding)      | Usually Vertical                  |
| Best For       | Real-time analytics, NoSQL | Structured data, complex joins    |


**Where MongoDB is Used**
- Content management systems
- E-commerce product catalogs
- Real-time analytics dashboards
- IoT data collection
- Mobile apps and microservices


**⚙️ How MongoDB Works Internally**
- Storage Engine (WiredTiger):
    - Handles reading/writing to disk
    - Uses compression and indexes for efficiency

Collections & Documents:
Collections are dynamic (schema-less)
Documents are stored in BSON (binary JSON) format

Indexes:
Automatically created on _id field
Support single-field, compound, geospatial, text indexes, etc.

Replication:
Uses Replica Sets for high availability (1 primary + multiple secondaries)

Sharding:
Distributes data across multiple servers for horizontal scaling


**💻 Installing MongoDB**
- Option 1: Local Installation
    - Download from: https://www.mongodb.com/try/download/community
    - Install MongoDB and mongosh (MongoDB Shell)
    - Start the MongoDB service:
    - mongod (server), mongosh (client)

- Option 2: MongoDB Atlas (Cloud)
    - Go to: https://www.mongodb.com/cloud/atlas
    - Free-tier available (ideal for practice)    
    - Create cluster → Connect via MongoDB URI or Compass

**🧭 MongoDB Compass GUI**
- Official GUI client for MongoDB
    - Visual interface to:
    - Connect to MongoDB (local or Atlas)
    - Browse databases & collections
    - Run queries and aggregations
    - Create indexes, validate schemas
- Great for non-command-line users

**🔧 Basic JSON & BSON Structure**
- JSON Example (JavaScript Object Notation):
```java
{
  "name": "Alice",
  "age": 30,
  "email": "alice@example.com",
  "address": {
    "city": "New York",
    "zip": "10001"
  }
}
```

**BSON (Binary JSON):**
- MongoDB stores documents as BSON
- Supports additional types like Date, Binary, ObjectId
- More efficient for storage & traversal
- Example BSON Types:
    - `ObjectId("507f1f77bcf86cd799439011")`  
    - `ISODate("2025-07-21T00:00:00Z")`


---

## 📘 MongoDB Data Types 
**🔸 1. String (String)**
- Stores textual data.
- Default type for fields like name, email.
```js
{ name: "Rahul Sharma", company: "TCS" }
```

**🔸 2. Number**
-  Integer (int32, int64)
  - Whole numbers (e.g., age, experience)
  `{ age: 30, experience: 5 }`

- Double (double)
  - For floating-point values.
  - `{ rating: 4.5 }`

**🔸 3. Boolean (bool)**
- Stores true or false.
- `{ isActive: true }`


**🔸 4. Date (Date)**
- Stored as ISODate.
- Used for timestamps like createdAt, doj, etc.
```js
{ doj: ISODate("2020-01-10T00:00:00Z") }
```
- Use new Date("YYYY-MM-DD") when inserting.

**🔸 5. Array (array)**
- Stores multiple values.
- Used for fields like skills, hobbies.
```js
{ skills: ["Java", "Spring Boot", "MongoDB"] }
```

**🔸 6. Embedded Document (object)**
- Nested object structure inside a document.
```js
{
  address: {
    street: "12 MG Road",
    city: "Bengaluru",
    state: "Karnataka"
  }
}
```

**🔸 7. ObjectId**
- Default ID (_id) in MongoDB documents.
- `{ _id: ObjectId("64a2fba8934e1e6d7b5c4c9b") }`
- You can also generate your own using ObjectId().

**🔸 8. Null**
- Represents an empty or missing value.
- `{ middleName: null }`

**🔸 9. Binary Data (binData)**
- Used to store files, images, encrypted data.
```js
{ profilePic: BinData(0, "base64EncodedValue") }
```

**🔸 10. Timestamp**
- Stores internal timestamps (different from Date).
- Often used in replication logs.

**🔸 11. Decimal128**
- High-precision floating point, useful for currency.

**🛠️ Example**
```js
{
  empId: "EMP001",                     // String
  age: 29,                             // Int
  doj: ISODate("2019-06-15"),         // Date
  isActive: true,                     // Boolean
  skills: ["Java", "Spring Boot"],    // Array
  address: {                          // Embedded document
    city: "Bengaluru",
    state: "Karnataka"
  },
  profilePic: BinData(0, "...."),     // Binary (optional)
  netWorth: NumberDecimal("100000.75")// Decimal128 (optional)
}
```

**🔍 Tips**
| Operation           | Example                                    |
| ------------------- | ------------------------------------------ |
| Filter by date      | `{ doj: { $gte: ISODate("2021-01-01") } }` |
| Match inside array  | `{ skills: "MongoDB" }`                    |
| Query nested object | `{ "address.city": "Mumbai" }`             |
| Check null          | `{ middleName: null }`                     |


**💡 Best Practices**
- Use ISODate for all date-related fields.
- Use ObjectId only for _id, unless custom needed.
- Avoid using mixed types in the same field.
- Prefer NumberDecimal for money-related fields.

**What is _id in MongoDB?**
- Every document in MongoDB must have a unique _id field.
- This _id acts as the primary key for the document.
- If you don't provide _id manually, MongoDB will automatically generate one using ObjectId().

**🔹 Why use _id?**
- Uniqueness: Prevents duplicate records.
- Indexing: MongoDB automatically indexes _id, improving query speed.
- Referencing: Used as foreign keys in relationships between collections.
- Sorting by creation time: Since ObjectId includes timestamp, you can sort by _id to get oldest/newest documents.

**🔹 What is ObjectId()?**
- ObjectId() is the default data type for _id.
- It is a 12-byte unique identifier, generated by MongoDB.
- Structure of ObjectId:

| Bytes | Description            |
| ----- | ---------------------- |
| 4     | Timestamp (Unix time)  |
| 3     | Machine Identifier     |
| 2     | Process ID             |
| 3     | Counter (unique value) |

```js
{
  _id: ObjectId("64e56c8e5a3d9f78907d2b4a"),
  name: "Priya Verma"
}
```

- ⚠️ This ObjectId is guaranteed to be unique even across machines and time.


---
**💬 MongoDB Shell (mongosh)**
- Interactive terminal to connect and manage MongoDB
- Use it for:
    - CRUD operations
    - Querying collections
    - Admin tasks (user roles, indexing, etc.)

**🏁 1. Database Commands**


| Command             | Description                 |
| ------------------- | --------------------------- |
| `show dbs`          | List all databases          |
| `use <dbname>`      | Switch to/create a database |
| `db`                | Show current database       |
| `db.dropDatabase()` | Delete the current database |

**📦 2. Collection Commands**
| Command                       | Description                              |
| ----------------------------- | ---------------------------------------- |
| `show collections`            | List all collections in the current DB   |
| `db.createCollection("name")` | Create a new collection                  |
| `db.<collection>.drop()`      | Drop a collection                        |
| `db.getCollectionNames()`     | Returns an array of all collection names |

**🧾 3. Document (CRUD) Commands**
```js
// 🟢 Create
db.collection.insertOne({...})
db.collection.insertMany([{...}, {...}])

// 🔵 Read
db.collection.find()                         // All docs
db.collection.find({ key: value })           // Filtered
db.collection.findOne({ key: value })
db.collection.find().pretty()                // Nicely formatted

// 🟠 Update
db.collection.updateOne(
  { filter }, 
  { $set: { key: value } }
)

db.collection.updateMany(
  { filter }, 
  { $inc: { count: 1 } }
)

db.collection.replaceOne({ filter }, { newDoc })


// 🔴 Delete
db.collection.deleteOne({ key: value })
db.collection.deleteMany({ key: value })
```

**🔍 4. Query Operators**
| Type       | Example Operators                                         |
| ---------- | --------------------------------------------------------- |
| Comparison | `$eq`, `$ne`, `$gt`, `$gte`, `$lt`, `$lte`, `$in`, `$nin` |
| Logical    | `$and`, `$or`, `$not`, `$nor`                             |
| Element    | `$exists`, `$type`                                        |
| Evaluation | `$regex`, `$expr`, `$mod`, `$text`                        |
| Array      | `$all`, `$elemMatch`, `$size`                             |
| Projection | `{ field: 1 }`, `{ field: 0 }`                            |


**🧱 5. Indexes**
```js
db.collection.createIndex({ key: 1 })           // Ascending
db.collection.createIndex({ key: -1 })          // Descending
db.collection.getIndexes()
db.collection.dropIndex({ key: 1 })
db.collection.dropIndexes()                     // Drop all
```

**📊 6. Aggregation Framework**
```js
db.collection.aggregate([
  { $match: { status: "active" } },
  { $group: { _id: "$category", total: { $sum: 1 } } },
  { $sort: { total: -1 } }
])
```

- Popular Aggregation Stages: $match, $group, $project, $sort, $limit, $skip, $unwind, $lookup, $count

**👮‍♂️ 7. User & Role Management**
```js
use admin

db.createUser({
  user: "admin",
  pwd: "password",
  roles: [ { role: "userAdminAnyDatabase", db: "admin" } ]
})

db.auth("admin", "password")
db.dropUser("admin")
```

**🛠 8. Utility & Admin Commands**
```js
| Command                      | Description                           |
| ---------------------------- | ------------------------------------- |
| `db.stats()`                 | Show stats of current DB              |
| `db.serverStatus()`          | Detailed server info                  |
| `db.currentOp()`             | Show current operations               |
| `db.shutdownServer()`        | Shutdown the server (requires access) |
| `db.isMaster()`              | Check if node is primary              |
| `db.getSiblingDB("otherdb")` | Access another DB without switching   |
```

**☁️ 9. Connecting to MongoDB**
```js
mongosh                         // Default local
mongosh "mongodb+srv://<URI>"   // Cloud (MongoDB Atlas)
```

**📌 10. BSON Types and Helpers**
```js
| Command                   | Description         |
| ------------------------- | ------------------- |
| `ObjectId("id")`          | Creates an ObjectId |
| `ISODate("2024-01-01")`   | Creates a date      |
| `NumberInt(10)`           | Force integer       |
| `NumberLong(10000000000)` | 64-bit integer      |
| `UUID("...")`             | Create UUID         |
```

---
### Test Data 
```js
[
  {
    empId: "EMP001",
    name: "Rahul Sharma",
    company: "Tata Consultancy Services",
    salary: 850000,
    age: 29,
    doj: ISODate("2019-06-15T00:00:00Z"),
    experience: 5,
    address: {
      street1: "12 MG Road",
      street2: "Near Central Mall",
      city: "Bengaluru",
      state: "Karnataka",
      pincode: "560001"
    },
    skills: ["Java", "Spring Boot", "Microservices"]
  },
  {
    empId: "EMP002",
    name: "Priya Verma",
    company: "Infosys",
    salary: 720000,
    age: 27,
    doj: ISODate("2020-01-10T00:00:00Z"),
    experience: 3,
    address: {
      street1: "45 Park Street",
      street2: "Flat No 5B",
      city: "Pune",
      state: "Maharashtra",
      pincode: "411001"
    },
    skills: ["Python", "Django", "REST APIs"]
  },
  {
    empId: "EMP003",
    name: "Amitabh Joshi",
    company: "Wipro",
    salary: 960000,
    age: 31,
    doj: ISODate("2017-09-23T00:00:00Z"),
    experience: 7,
    address: {
      street1: "77 Nehru Nagar",
      street2: "Opposite Bus Stand",
      city: "Hyderabad",
      state: "Telangana",
      pincode: "500003"
    },
    skills: ["Angular", "TypeScript", "Node.js"]
  },
  {
    empId: "EMP004",
    name: "Sneha Reddy",
    company: "HCL Technologies",
    salary: 650000,
    age: 25,
    doj: ISODate("2021-04-01T00:00:00Z"),
    experience: 2,
    address: {
      street1: "Plot 19, Jubilee Hills",
      street2: "Road No 10",
      city: "Hyderabad",
      state: "Telangana",
      pincode: "500033"
    },
    skills: ["JavaScript", "React", "HTML", "CSS"]
  },
  {
    empId: "EMP005",
    name: "Vikram Mehta",
    company: "Tech Mahindra",
    salary: 1030000,
    age: 35,
    doj: ISODate("2015-02-18T00:00:00Z"),
    experience: 10,
    address: {
      street1: "34 Ashok Nagar",
      street2: "Near SBI Bank",
      city: "Chennai",
      state: "Tamil Nadu",
      pincode: "600083"
    },
    skills: ["DevOps", "AWS", "Docker", "Kubernetes"]
  },
  {
    empId: "EMP006",
    name: "Neha Kulkarni",
    company: "Capgemini",
    salary: 780000,
    age: 28,
    doj: ISODate("2018-07-12T00:00:00Z"),
    experience: 6,
    address: {
      street1: "9 FC Road",
      street2: "Opposite CCD",
      city: "Pune",
      state: "Maharashtra",
      pincode: "411004"
    },
    skills: ["Data Analysis", "SQL", "Power BI"]
  },
  {
    empId: "EMP007",
    name: "Rohan Singh",
    company: "Mindtree",
    salary: 690000,
    age: 26,
    doj: ISODate("2020-09-21T00:00:00Z"),
    experience: 3,
    address: {
      street1: "C-15 Sector 62",
      street2: "Near Fortis Hospital",
      city: "Noida",
      state: "Uttar Pradesh",
      pincode: "201309"
    },
    skills: ["C#", ".NET Core", "Azure"]
  },
  {
    empId: "EMP008",
    name: "Divya Nair",
    company: "Cognizant",
    salary: 880000,
    age: 30,
    doj: ISODate("2016-03-10T00:00:00Z"),
    experience: 8,
    address: {
      street1: "45 Marine Drive",
      street2: "Flat No 302",
      city: "Mumbai",
      state: "Maharashtra",
      pincode: "400002"
    },
    skills: ["UI/UX Design", "Figma", "Adobe XD"]
  },
  {
    empId: "EMP009",
    name: "Arjun Desai",
    company: "L&T Infotech",
    salary: 940000,
    age: 32,
    doj: ISODate("2014-12-05T00:00:00Z"),
    experience: 9,
    address: {
      street1: "12 Patel Street",
      street2: "Next to Axis Bank",
      city: "Ahmedabad",
      state: "Gujarat",
      pincode: "380015"
    },
    skills: ["Oracle", "PL/SQL", "Data Warehousing"]
  },
  {
    empId: "EMP010",
    name: "Meena Iyer",
    company: "Zensar Technologies",
    salary: 710000,
    age: 27,
    doj: ISODate("2019-11-30T00:00:00Z"),
    experience: 4,
    address: {
      street1: "98 T Nagar",
      street2: "Behind Big Bazaar",
      city: "Chennai",
      state: "Tamil Nadu",
      pincode: "600017"
    },
    skills: ["Testing", "Selenium", "JIRA"]
  }
]
```

### MongoDB Operations 
```js
use company
db.createCollection("employees")
```

#### ✅ 1. Insert Operations
**Insert one document**
```js
db.employees.insertOne({
  empId: "EMP011",
  name: "Kiran Rao",
  company: "TCS",
  salary: 800000,
  age: 30,
  doj: "2018-08-20",
  experience: 6,
  address: {
    street1: "23 Residency Road",
    street2: "Near Metro",
    city: "Bengaluru",
    state: "Karnataka",
    pincode: "560025"
  },
  skills: ["Java", "Spring", "Hibernate"]
});
```

**Insert many documents**
```js
db.employees.insertMany([/* full list of 10 you shared */]);
```

### 🔍 2. Find / Query Operations
```js
db.collection.find(query, projection)

- 1 = include field
- 0 = exclude field
- You can't mix 1 and 0 in the same projection (except _id)

```

**Find all employees**
```js
db.employees.find();
```

**Show only empId, name, and city**
```js
db.employees.find({}, {
  empId: 1,
  name: 1,
  "address.city": 1,
  _id: 0
});
```

**Find employees from "Infosys"**
```js
db.employees.find({ company: "Infosys" });
```

**Find with multiple conditions (age > 28 and salary > 800000)**
```js
db.employees.find({ age: { $gt: 28 }, salary: { $gt: 800000 } });
```

**Find with nested field (state = "Karnataka")**
```js
db.employees.find({ "address.state": "Karnataka" });
```

**Find using skill array (has "React")**
```js
db.employees.find({ skills: "React" });
```

**Find with regular expression (names starting with A)**
```js
db.employees.find({ name: /^A/ });
```

### ✏️ 3. Update Operations

**Update one employee's salary**
```js
db.employees.updateOne(
  { empId: "EMP001" },
  { $set: { salary: 900000 } }
);
```

**Update all employees in "Hyderabad" city with 10% salary increase**
```js
db.employees.updateMany(
  { "address.city": "Hyderabad" },
  { $mul: { salary: 1.1 } }
);
```

**Add a skill to one employee**
```js
db.employees.updateOne(
  { empId: "EMP004" },
  { $addToSet: { skills: "Redux" } }
);
```

### ❌ 4. Delete Operations
**Delete one employee**
```js
db.employees.deleteOne({ empId: "EMP005" });
```

**Delete employees with less than 2 years experience**
```js
db.employees.deleteMany({ experience: { $lt: 2 } });
```

### 📊 5. Aggregation Examples
**Count employees in each company**
```js
db.employees.aggregate([
  { $group: { _id: "$company", count: { $sum: 1 } } }
]);
```

**Average salary by city**
```js
db.employees.aggregate([
  { $group: { _id: "$address.city", avgSalary: { $avg: "$salary" } } }
]);
```

**List of employees by skill (unwind array)**
```js
db.employees.aggregate([
  { $unwind: "$skills" },
  { $group: { _id: "$skills", count: { $sum: 1 } } },
  { $sort: { count: -1 } }
]);
```

### 🔍 6. Projection
**Get only name and company**
```js
db.employees.find({}, { name: 1, company: 1, _id: 0 });
```

**Exclude address and skills**
```js
db.employees.find({}, { address: 0, skills: 0 });
```

### 📌 7. Indexing

**Create index on empId**
```js
db.employees.createIndex({ empId: 1 }, { unique: true });
```

**Index on city for faster location queries**
```js
db.employees.createIndex({ "address.city": 1 });
```

### 🧪 8. Other Useful Operations

**Sort employees by salary (descending)**
```js
db.employees.find().sort({ salary: -1 });
```

**Limit and skip**
```js
db.employees.find().sort({ name: 1 }).limit(5).skip(5);
```

**Distinct list of states**
```js
db.employees.distinct("address.state");
```

**Replace One**
```js
db.employees.replaceOne(
  { empId: "EMP002" },
  {
    empId: "EMP002",
    name: "Priya Verma",
    company: "Infosys",
    salary: 740000,
    age: 28,
    doj: "2020-01-10",
    experience: 4,
    address: {
      street1: "New Lane 22",
      street2: "Sector 7",
      city: "Pune",
      state: "Maharashtra",
      pincode: "411045"
    },
    skills: ["Python", "Flask"]
  }
);
```

### 🔍 Query by Date
**Find employees who joined after 2020-01-01**
```js
db.employees.find({
  doj: { $gt: new Date("2020-01-01") }
});
```

**Joined between 2018 and 2020**
```js
db.employees.find({
  doj: {
    $gte: new Date("2018-01-01"),
    $lte: new Date("2020-12-31")
  }
});
```

**Group by year of joining**
```js
db.employees.aggregate([
  {
    $group: {
      _id: { $year: "$doj" },
      count: { $sum: 1 }
    }
  },
  { $sort: { "_id": 1 } }
]);
```

**Group by month of joining**
```js
db.employees.aggregate([
  {
    $group: {
      _id: {
        year: { $year: "$doj" },
        month: { $month: "$doj" }
      },
      count: { $sum: 1 }
    }
  }
]);
```

**Calculate Experience from doj**
```js
db.employees.aggregate([
  {
    $project: {
      name: 1,
      doj: 1,
      experienceYears: {
        $dateDiff: {
          startDate: "$doj",
          endDate: "$$NOW",
          unit: "year"
        }
      }
    }
  }
]);
```

**Find employees who joined in the last 1 year:**
```js
const oneYearAgo = new Date();
oneYearAgo.setFullYear(oneYearAgo.getFullYear() - 1);

db.employees.find({
  doj: { $gte: oneYearAgo }
});
```