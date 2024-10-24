import os

# Specify the root directory where the dated folders are located
root_dir = "root_dir"
# Output file to store all JavaScript content
output_file = os.path.join(root_dir, "AllSrc.js")

def collect_js_files(root, output):
    with open(output, 'w', encoding='utf-8') as outfile:
        for subdir, dirs, files in os.walk(root):
            for file in files:
                if file.endswith('.js'):
                    file_path = os.path.join(subdir, file)
                    # Write a comment indicating the original location
                    outfile.write(f"\n// Source: {file_path}\n")
                    with open(file_path, 'r', encoding='utf-8') as infile:
                        # Copy the content of each JavaScript file
                        outfile.write(infile.read())
                        outfile.write("\n")  # Add some spacing between files

if __name__ == "__main__":
    collect_js_files(root_dir, output_file)
    print(f"JavaScript files have been collected into {output_file}")
