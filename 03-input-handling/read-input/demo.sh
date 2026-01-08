#!/bin/bash

# Demo script to test the Input Validation program
# This shows how the program handles different types of input

echo "======================================"
echo "  Input Validation Demo Test Script"
echo "======================================"
echo ""

cd "$(dirname "$0")"

# Compile the Java files
echo "📦 Compiling Java files..."
javac src/*.java

if [ $? -eq 0 ]; then
    echo "✅ Compilation successful!"
    echo ""
else
    echo "❌ Compilation failed!"
    exit 1
fi

echo "======================================"
echo "  Test Scenario 1: All Valid Input"
echo "======================================"
echo "Entering: Age=25, Name=John Doe, Salary=50000"
echo "Then: Age=30, Name=Jane Smith, Salary=60000"
echo "Then: Age=28, Name=Alice Johnson, Salary=75000"
echo ""

# Create input file for testing
cat > test_input_valid.txt << EOF
25
John Doe
50000
30
Jane Smith
60000
28
Alice Johnson
75000
EOF

java -cp src Main < test_input_valid.txt

echo ""
echo ""
echo "======================================"
echo "  Test Scenario 2: Invalid Input Test"
echo "======================================"
echo "This will demonstrate error handling in Method 3"
echo "Try entering invalid data when prompted:"
echo "  - Letters for age (e.g., 'abc')"
echo "  - Negative numbers"
echo "  - Numbers out of range (e.g., age > 150)"
echo "  - Empty input"
echo ""
echo "Note: Methods 1 & 2 will crash/fail, but Method 3 will handle errors gracefully!"
echo ""
echo "Press Enter to start interactive test..."
read

java -cp src Main

# Cleanup
rm -f test_input_valid.txt

echo ""
echo "======================================"
echo "  Demo Complete!"
echo "======================================"
echo ""
echo "Key Takeaways:"
echo "✅ Method 3 never crashes on invalid input"
echo "✅ Clear error messages guide the user"
echo "✅ Input validation prevents bad data"
echo "✅ Production-ready code handles all edge cases"
echo ""

