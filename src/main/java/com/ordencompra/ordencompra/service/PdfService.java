package com.ordencompra.ordencompra.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.ordencompra.ordencompra.model.DetalleVenta;
import com.ordencompra.ordencompra.model.OrdenCompra;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;



@Service
public class PdfService {

    private final SpringTemplateEngine templateEngine;

    private static final double IVA_RATE = 0.19d;

    public PdfService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    public byte[] generarOrdenPdf(OrdenCompra orden) throws IOException {
        try {

            List<DetalleVenta> detalles = orden.getDetalleVenta();

            double totalBruto = 0.0d;
            if (detalles != null) {
                for (DetalleVenta d : detalles) {
                    Double precio = d.getPrecioUnitario() == null ? 0.0d : d.getPrecioUnitario();
                    int cantidad = d.getCantidad();
                    totalBruto += precio * cantidad;
                }
            }
            totalBruto = round2(totalBruto);
            double iva = round2(totalBruto * IVA_RATE);
            double totalConIva = round2(totalBruto + iva);

            Context ctx = new Context();
            ctx.setVariable("orden", orden);
            ctx.setVariable("totalBruto", totalBruto);
            ctx.setVariable("iva", iva);
            ctx.setVariable("totalConIva", totalConIva);

            String html = templateEngine.process("orden-template", ctx);

            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                PdfRendererBuilder builder = new PdfRendererBuilder();
                builder.useFastMode();

                InputStream test = getClass().getResourceAsStream("/fonts/DejaVuSans.ttf");
                if (test != null) {
                    try { test.close(); } catch (Exception ignored) {}
                    builder.useFont(() -> getClass().getResourceAsStream("/fonts/DejaVuSans.ttf"), "DejaVu Sans");
                }

                builder.withHtmlContent(html, "");
                builder.toStream(os);
                builder.run();

                return os.toByteArray();
            }
        } catch (Exception e) {
            throw new IOException("Error generando PDF: " + e.getMessage(), e);
        }
    }

    private static double round2(double val) {
        return BigDecimal.valueOf(val).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
