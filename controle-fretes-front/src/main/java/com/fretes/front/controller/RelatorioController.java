package com.fretes.front.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fretes.front.model.Fretes;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class RelatorioController {

    @FXML
    private TableView<Fretes> tblFretes;

    @FXML
    private TextField txtBusca;

    private ObservableList<Fretes> lista = FXCollections.observableArrayList();

    // Lista filtrada para buscar
    private FilteredList<Fretes> listaFiltrada;

    // Método para carregar dados de exemplo (ou do seu banco)
    public void carregarDados(ObservableList<Fretes> dados) {
        lista = dados;
        listaFiltrada = new FilteredList<>(lista, p -> true);
        tblFretes.setItems(listaFiltrada);
    }

    @FXML
    private void buscar() {
        String filtro = txtBusca.getText().toLowerCase();
        listaFiltrada.setPredicate(f -> {
            if (filtro == null || filtro.isEmpty()) return true;
            return String.valueOf(f.getNumeroPedido()).toLowerCase().contains(filtro) ||
                   String.valueOf(f.getNumeroNF()).toLowerCase().contains(filtro) ||
                   f.getTransportadora().toLowerCase().contains(filtro);
        });
    }

    @FXML
    private void atualizar() {
        txtBusca.clear();
        listaFiltrada.setPredicate(p -> true);
        tblFretes.refresh();
    }

    @FXML
    public void exportarExcel() {
        if (lista.isEmpty()) return;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File file = fileChooser.showSaveDialog(tblFretes.getScene().getWindow());
        if (file == null) return;

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Fretes");

            // Cabeçalho
            Row header = sheet.createRow(0);
            String[] colunas = {"ID", "Pedido", "Transportadora", "Peso", "Cubagem", "Valor", "NF", "CTe", "Status"};
            for (int i = 0; i < colunas.length; i++) {
                header.createCell(i).setCellValue(colunas[i]);
            }

            // Dados
            int rowNum = 1;
            for (Fretes f : listaFiltrada) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(f.getId());
                row.createCell(1).setCellValue(f.getNumeroPedido());
                row.createCell(2).setCellValue(f.getTransportadora());
                row.createCell(3).setCellValue(f.getPeso());
                row.createCell(4).setCellValue(f.getCubagem());
                row.createCell(5).setCellValue(f.getValor());
                row.createCell(6).setCellValue(f.getNumeroNF());
                row.createCell(7).setCellValue(f.getNumeroCte());
                row.createCell(8).setCellValue(f.getStatus());
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }
            System.out.println("Excel exportado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exportarPDF() {
        if (lista.isEmpty()) return;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File file = fileChooser.showSaveDialog(tblFretes.getScene().getWindow());
        if (file == null) return;

        try {
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Título
            document.add(new Paragraph("Relatório de Fretes\n\n"));

            // Tabela PDF
            float[] columnWidths = {30f, 50f, 80f, 40f, 50f, 50f, 50f, 50f, 60f};
            Table table = new Table(columnWidths);

            // Cabeçalho
            String[] colunas = {"ID", "Pedido", "Transportadora", "Peso", "Cubagem", "Valor", "NF", "CTe", "Status"};
            for (String c : colunas) {
                table.addCell(new Cell().add(new Paragraph(c)));
            }

            // Dados
            for (Fretes f : listaFiltrada) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(f.getId()))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(f.getNumeroPedido()))));
                table.addCell(new Cell().add(new Paragraph(f.getTransportadora())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(f.getPeso()))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(f.getCubagem()))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(f.getValor()))));
                table.addCell(new Cell().add(new Paragraph(f.getNumeroNF())));
                table.addCell(new Cell().add(new Paragraph(f.getNumeroCte())));
                table.addCell(new Cell().add(new Paragraph(f.getStatus())));
            }

            document.add(table);
            document.close();

            System.out.println("PDF exportado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}