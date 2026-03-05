package com.fretes.service;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fretes.entity.TabelaFrete;
import com.fretes.entity.Transportadora;
import com.fretes.repository.TabelaFreteRepository;
import com.fretes.repository.TransportadoraRepository;

@Service
public class UploadTabelaFreteService {

    private final TabelaFreteRepository tabelaFreteRepository;
    private final TransportadoraRepository transportadoraRepository;

    public UploadTabelaFreteService(
            TabelaFreteRepository tabelaFreteRepository,
            TransportadoraRepository transportadoraRepository) {

        this.tabelaFreteRepository = tabelaFreteRepository;
        this.transportadoraRepository = transportadoraRepository;
    }

    public void upload(MultipartFile file) {

        try {

            InputStream is = file.getInputStream();

            Workbook workbook = WorkbookFactory.create(is);

            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();

            rows.next(); // pula cabeçalho

            while (rows.hasNext()) {

                Row row = rows.next();

                Long transportadoraId = (long) row.getCell(0).getNumericCellValue();
                String estado = row.getCell(1).getStringCellValue();
                String cidade = row.getCell(2).getStringCellValue();
                double pesoMin = row.getCell(3).getNumericCellValue();
                double pesoMax = row.getCell(4).getNumericCellValue();
                double valor = row.getCell(5).getNumericCellValue();

                Transportadora transportadora =
                        TransportadoraRepository.findById(transportadoraId).orElseThrow();

                TabelaFrete tabela = new TabelaFrete();

                tabela.setTransportadora(transportadora);
                tabela.setEstadoDestino(estado);
                tabela.setCidadeDestino(cidade);
                tabela.setPesoMin(pesoMin);
                tabela.setPesoMax(pesoMax);
                tabela.setValor(valor);

                tabelaFreteRepository.save(tabela);
            }

            workbook.close();

        } catch (Exception e) {

            throw new RuntimeException("Erro ao processar arquivo");
        }
    }
}