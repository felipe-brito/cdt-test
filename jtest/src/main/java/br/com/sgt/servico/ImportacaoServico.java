package br.com.sgt.servico;

import br.com.sgt.controller.ImportacaoController;
import br.com.sgt.controller.ImportacaoResponse;
import br.com.sgt.entidades.Cabecalho;
import br.com.sgt.entidades.Requisicao;
import br.com.sgt.enums.ChavesMapas;
import static br.com.sgt.enums.MensagensExceptionSGT.*;
import br.com.sgt.enums.StatusBarraProgresso;
import br.com.sgt.exception.LogTraceException;
import br.com.sgt.util.Propriedades;
import br.com.sgt.util.Random;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.PatternSyntaxException;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.NoArgsConstructor;

/**
 *
 * @author Felipe de Brito Lira
 */
@NoArgsConstructor
public class ImportacaoServico {

    @Inject
    private ImportacaoController controller;

    @Inject
    private Propriedades properties;

    public List<Requisicao> requests;
    public Set<Cabecalho> cabecalhos;
    public Map<ChavesMapas, Object> mapa = Maps.newConcurrentMap();;

    @PostConstruct
    private void init() {
        requests = Lists.newArrayList();
        cabecalhos = Sets.newHashSet();
    }

    private final Integer CEM = 100;
    private final Integer ZERO = 0;

    public Map<ChavesMapas, Object> importarRquests(File file) throws LogTraceException {

        init();
        ImportacaoResponse importacaoResponse = new ImportacaoResponse(0, 0, 0, 0, StatusBarraProgresso.START);
        Set<String> headers = Sets.newHashSet();
        mapa.put(ChavesMapas.CANCELADO, Boolean.FALSE);
        
        int count = 0, erro = 0, sucesso = 0;
        StatusBarraProgresso status = StatusBarraProgresso.START;

        try {

            controller.notificarImportacaoObeserver(importacaoResponse);

            List<String> linhas = Files.readAllLines(Paths.get(file.getAbsolutePath()));

            BigDecimal porcentagem = new BigDecimal(linhas.size()).divide(new BigDecimal(CEM), RoundingMode.CEILING);
            importacaoResponse.setTotalImportacoes(linhas.size());

            for (int i = 0; i < linhas.size(); i++) {

                if (canelarProcesso() || pausarProcesso()) {
                    break;
                }

                try {

                    String linha = linhas.get(i);
                    String entrada = linha.substring(linha.indexOf(properties.getJson().getStartWith()));

                    if (entrada != null && !entrada.trim().isEmpty()) {
                        Requisicao request = new Gson().fromJson(entrada, Requisicao.class);
                        request.setChave(Random.getString());
                        requests.add(request);
                        headers.addAll(request.getHeaders().keySet());
                        ++sucesso;
                    }

                } catch (JsonSyntaxException | PatternSyntaxException e) {
                    ++erro;
                }

                if (i % porcentagem.intValue() == ZERO) {
                    ++count;
                    if (count >= CEM) {
                        status = StatusBarraProgresso.STOP;
                    }
                }

                notificarBarraProgresso(importacaoResponse, sucesso, erro, count, StatusBarraProgresso.START);
            }

            headers.forEach((header) -> {
                cabecalhos.add(new Cabecalho(header));
            });

            mapa.put(ChavesMapas.REQUEST, requests);
            mapa.put(ChavesMapas.HEADERS, cabecalhos);

            notificarBarraProgresso(importacaoResponse, sucesso, erro, CEM, status);

        } catch (IOException ioe) {
            throw new LogTraceException(ERRO_LEITURA_ARQUIVO.getErro() + ioe.getCause().toString());
        }

        confirmarProcesso();

        return mapa;

    }

    private void notificarBarraProgresso(ImportacaoResponse importacaoResponse, Integer valorTotalSucesso, Integer valorTotalErro, Integer valorProgresso, StatusBarraProgresso statusBarra) {
        try {

            importacaoResponse.setValorImportacoesSucesso(valorTotalSucesso);
            importacaoResponse.setValorImportacoesErro(valorTotalErro);
            importacaoResponse.setValorProgresso(valorProgresso);
            importacaoResponse.setStatusBarraProgresso(statusBarra);

            controller.notificarImportacaoObeserver(importacaoResponse);

            Thread.sleep(10);

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private Boolean canelarProcesso() {
        if (this.controller.getCancelarImportacao()) {
            mapa.put(ChavesMapas.CANCELADO, Boolean.TRUE);
            init();
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean pausarProcesso() {
        Boolean estado = Boolean.FALSE;
        while (this.controller.getPauseImportacao()) {
            LocalDate.now();
            if (this.controller.getCancelarImportacao()) {
                this.controller.setPauseImportacao(Boolean.FALSE);
                estado = Boolean.TRUE;
            }
        }
        return estado;
    }

    private void confirmarProcesso() {
        while (this.controller.getConfirmarImportacao()) {
            LocalDate.now();
        }
    }

}
