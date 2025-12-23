import os
from pypdf import PdfReader

def extract_text_from_pdf(pdf_path):
    try:
        reader = PdfReader(pdf_path)
        text = ""
        for page in reader.pages:
            text += page.extract_text() + "\n"
        return text
    except Exception as e:
        return f"Error reading {pdf_path}: {str(e)}"

# List of directories to process
directories = ["Labos", "Théorie"]
output_file = "FULL_COURSE_CONTEXT.txt"

with open(output_file, "w") as out_f:
    for directory in directories:
        if os.path.exists(directory):
            files = sorted([f for f in os.listdir(directory) if f.endswith(".pdf")])
            for filename in files:
                filepath = os.path.join(directory, filename)
                print(f"Processing {filepath}...")
                out_f.write(f"\n\n{'='*50}\nFILE: {filepath}\n{'='*50}\n\n")
                content = extract_text_from_pdf(filepath)
                out_f.write(content)

print(f"All PDF content extracted to {output_file}")

