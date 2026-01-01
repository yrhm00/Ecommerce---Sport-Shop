#!/bin/bash
# Script to initialize the database for Ecommerce Sport Shop

DB_NAME="janvier_db"

echo "Initializing database setup..."

# Check if createdb is available
if ! command -v createdb &> /dev/null; then
    echo "❌ Error: PostgreSQL tools not found."
    echo "Please install PostgreSQL and ensure 'createdb' is in your PATH."
    exit 1
fi

# Try to create the database
if createdb "$DB_NAME" 2>/dev/null; then
    echo "✅ Database '$DB_NAME' created successfully."
else
    echo "⚠️  Database '$DB_NAME' creation failed. It likely already exists."
fi

echo "---------------------------------------------------"
echo "🚀 You can now run the application with:"
echo "   mvn spring-boot:run"
