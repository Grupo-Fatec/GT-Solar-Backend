# PDF Generator Service

This branch implements a basic PDF Generator Service for the GT-Solar Backend project. The service provides an endpoint to generate and download a simple PDF file.

## Features

- **PDF Generation Endpoint:**  
  Access the endpoint at `/pdf/generate` to download a PDF file containing a title and a paragraph.
- **Spring Boot Integration:**  
  The service is built using Spring Boot and leverages the [OpenPDF](https://github.com/LibrePDF/OpenPDF) library for PDF creation.
- **Customizable Output:**  
  The generated PDF currently includes a centered title and a left-aligned paragraph as a demonstration.

## Usage

1. Start the application.
2. Navigate to `http://localhost:<port>/pdf/generate` in your browser or use a tool like `curl` or Postman.
3. A PDF file will be downloaded with a timestamped filename.

## Future Improvements

This PDF Generator Service is a starting point and will be incremented in the future to support:
- Dynamic content generation based on user input or database data.
- Enhanced formatting and styling options.
- Support for tables, images, and other PDF features.
- Integration with other modules of the GT-Solar Backend.

Stay tuned for updates as the service evolves!