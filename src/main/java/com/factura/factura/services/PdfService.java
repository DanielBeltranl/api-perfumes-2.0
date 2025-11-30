package com.factura.factura.services;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    private final SpringTemplateEngine templateEngine;

    public PdfService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    public byte[] generarFacturaPdf(Object factura, double totalBruto, double iva, double totalConIva) throws IOException {
        Context context = new Context();
        context.setVariable("factura", factura);
        context.setVariable("totalBruto", totalBruto);
        context.setVariable("iva", iva);
        context.setVariable("totalConIva", totalConIva);

        // Renderiza la plantilla Thymeleaf a HTML
        String html = templateEngine.process("factura-template", context);

        // Convierte HTML a PDF con OpenHTMLToPDF
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            // Si necesitas resolver recursos (imagenes/css) desde classpath, pasa baseUri: "classpath:/static/" o similar
            builder.withHtmlContent(html, null);
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        } catch (Exception e) {
            throw new IOException("Error generando PDF: " + e.getMessage(), e);
        }
    }
}