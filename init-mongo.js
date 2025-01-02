db = db.getSiblingDB("credits");

db.createUser({
  user: "mongoadmin",
  pwd: "secret",
  roles: [
    { role: "readWrite", db: "credits" },
    { role: "read", db: "admin" }
  ]
});

print("MongoDB initialization script executed successfully.");
