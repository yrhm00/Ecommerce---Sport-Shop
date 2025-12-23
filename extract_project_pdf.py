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

# Target file
pdf_file = "Projet/Projet - Janvier.pdf"
if os.path.exists(pdf_file):
    print(f"--- Extracting content from {pdf_file} ---")
    content = extract_text_from_pdf(pdf_file)
    print(content)
else:
    print(f"File not found: {pdf_file}")

