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