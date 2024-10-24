def generate_test_files():
    try:
        # Circle test file
        with open('circle_test.txt', 'w') as file:
            file.write("78.54 C\n")
            file.write("10 10 5.0\n")

        # Triangle test file
        with open('triangle_test.txt', 'w') as file:
            file.write("43.30 T\n")
            file.write("15 15 5.0\n")

        # Square test file
        with open('square_test.txt', 'w') as file:
            file.write("25.0 S\n")
            file.write("20 20 5.0\n")

        # Hexagon test file
        with open('hexagon_test.txt', 'w') as file:
            file.write("64.95 H\n")
            file.write("5 5 10.0\n")

        # Largest enclosing rectangle test file
        with open('largest_enclosing_test.txt', 'w') as file:
            file.write("H 64.95\n")
            file.write("78.54 C 10 10 5.0\n")
            file.write("43.30 T 15 15 5.0\n")
            file.write("25.0 S 20 20 5.0\n")
            file.write("64.95 H 5 5 10.0\n")

        # Faulty input test file
        with open('faulty_input_test.txt', 'w') as file:
            file.write("C 10 -5.0\n")

        print("Test files generated successfully.")

    except IOError as e:
        print(f"Error generating test files: {e}")

if __name__ == "__main__":
    generate_test_files()
