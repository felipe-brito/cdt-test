package br.com.sgt.gui;

import br.com.sgt.controller.ListenerController;
import br.com.sgt.controller.ListenerImportacaoResponse;
import br.com.sgt.observer.ImportacaoListener;
import br.com.sgt.util.PathsImagens;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;
import javax.swing.ImageIcon;

/**
 *
 * @author Felipe de Brito Lira
 */
public class ImportacaoProgressoUI extends javax.swing.JDialog implements ImportacaoListener {

    @Inject
    private ListenerController controller;

    @Inject
    private PathsImagens pathsImagens;

    private Timer timer;

    /**
     * Creates new form ImportacaoProgressoUI
     */
    public ImportacaoProgressoUI() {
        initComponents();
        initView();
        initBarraIndeterminada();
    }

    private void initView() {
        this.setLocationRelativeTo(null);
        this.setModal(Boolean.TRUE);
        this.setResizable(Boolean.FALSE);
    }

    private void initBarraIndeterminada() {
        this.barraProgressoImportacao.setIndeterminate(Boolean.TRUE);
        this.barraProgressoImportacao.setStringPainted(Boolean.TRUE);
    }

    private void atualizarBarraProgresso(Integer valor) {
        this.barraProgressoImportacao.setValue(valor);
        this.barraProgressoImportacao.setString(String.format("%d%s", valor, "%"));
        this.barraProgressoImportacao.setStringPainted(Boolean.TRUE);
        this.barraProgressoImportacao.setIndeterminate(Boolean.FALSE);
    }

    private void setandoImagemSucessorPathsProcessado() {
        ImageIcon image = new ImageIcon(getClass().getResource(pathsImagens.getPathImagemSucesso()));
        this.imagemSucesso.setImage(image.getImage());
    }

    private void setandoImagemErroPathsProcessado() {
        ImageIcon image = new ImageIcon(getClass().getResource(pathsImagens.getPathImagemErro()));
        this.imagemErro.setImage(image.getImage());
    }

    private void cronometro() {
        timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            LocalTime dateTime = LocalTime.of(0, 0, 0);

            @Override
            public void run() {
                try {
                    if (dateTime.getSecond() <= 60) {
                        dateTime = dateTime.plusSeconds(1);
                    }
                    labelCronometro.setText(dateTime.format(DateTimeFormatter.ISO_TIME));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, 0, 1000);
    }

    private void atualizarStatusSucesso(Integer valor) {
        this.labelTotalSucesso.setText(String.valueOf(valor));
    }

    private void atualizarStatusErro(Integer valor) {
        this.labelTotalErro.setText(String.valueOf(valor));
    }

    private void atualizarTotalizado(Integer valor) {
        this.labelTotal.setText(String.valueOf(valor));
    }

    private void pausar() {
        this.controller.controlePauseImportacao(Boolean.TRUE);
        ativarBotoesControle(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
    }

    private void continuar() {
        this.controller.controlePauseImportacao(Boolean.FALSE);
        ativarBotoesControle(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
    }

    private void cancelar() {
        this.controller.controleCancelarImportacao(Boolean.FALSE);
        this.controller.controleConfirmarImportacao(Boolean.TRUE);
        this.controller.removerImportacaoObserver(this);
        this.dispose();
    }

    private void finalizar() {
        this.controller.controleConfirmarImportacao(Boolean.FALSE);
        this.controller.removerImportacaoObserver(this);
        this.dispose();
    }

    private void ativarBotoesControle(Boolean stop, Boolean pause, Boolean resume, Boolean finalizar) {
        this.pause.setEnabled(pause);
        this.stop.setEnabled(stop);
        this.resume.setEnabled(resume);
        this.finalizar.setEnabled(finalizar);
    }

    public void init() {
        setandoImagemSucessorPathsProcessado();
        setandoImagemErroPathsProcessado();
        cronometro();
        ativarBotoesControle(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
        this.setVisible(Boolean.TRUE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        barraProgressoImportacao = new javax.swing.JProgressBar();
        stop = new javax.swing.JButton();
        pause = new javax.swing.JButton();
        resume = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelTotalSucesso = new javax.swing.JLabel();
        imagemSucesso = new org.jdesktop.swingx.JXImagePanel();
        jPanel4 = new javax.swing.JPanel();
        labelTotalErro = new javax.swing.JLabel();
        imagemErro = new org.jdesktop.swingx.JXImagePanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelCronometro = new javax.swing.JLabel();
        finalizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(390, 388));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Importando", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        barraProgressoImportacao.setForeground(new java.awt.Color(102, 102, 255));
        barraProgressoImportacao.setToolTipText("");
        barraProgressoImportacao.setStringPainted(true);

        stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/stop.png"))); // NOI18N
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });

        pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pause.png"))); // NOI18N
        pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseActionPerformed(evt);
            }
        });

        resume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/play.png"))); // NOI18N
        resume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resumeActionPerformed(evt);
            }
        });

        jLabel9.setText("Total de Paths");

        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(labelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(labelTotal))
                .addContainerGap())
        );

        labelTotalSucesso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTotalSucesso.setText("0 / 0");

        javax.swing.GroupLayout imagemSucessoLayout = new javax.swing.GroupLayout(imagemSucesso);
        imagemSucesso.setLayout(imagemSucessoLayout);
        imagemSucessoLayout.setHorizontalGroup(
            imagemSucessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
        imagemSucessoLayout.setVerticalGroup(
            imagemSucessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(labelTotalSucesso, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagemSucesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTotalSucesso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(imagemSucesso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        labelTotalErro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTotalErro.setText("0 / 0");

        javax.swing.GroupLayout imagemErroLayout = new javax.swing.GroupLayout(imagemErro);
        imagemErro.setLayout(imagemErroLayout);
        imagemErroLayout.setHorizontalGroup(
            imagemErroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );
        imagemErroLayout.setVerticalGroup(
            imagemErroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(labelTotalErro, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagemErro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelTotalErro)
                .addContainerGap())
            .addComponent(imagemErro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel1.setText("Tempo decorrido");

        labelCronometro.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelCronometro.setText("00:00:00");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelCronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelCronometro))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        finalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/resume.png"))); // NOI18N
        finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(stop)
                        .addGap(26, 26, 26)
                        .addComponent(pause)
                        .addGap(26, 26, 26)
                        .addComponent(resume)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finalizar))
                    .addComponent(barraProgressoImportacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(barraProgressoImportacao, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(stop)
                        .addComponent(pause))
                    .addComponent(resume, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(finalizar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
        cancelar();
    }//GEN-LAST:event_stopActionPerformed

    private void pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseActionPerformed
        pausar();
    }//GEN-LAST:event_pauseActionPerformed

    private void resumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resumeActionPerformed
        continuar();
    }//GEN-LAST:event_resumeActionPerformed

    private void finalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarActionPerformed
        finalizar();
    }//GEN-LAST:event_finalizarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cancelar();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgressoImportacao;
    private javax.swing.JButton finalizar;
    private org.jdesktop.swingx.JXImagePanel imagemErro;
    private org.jdesktop.swingx.JXImagePanel imagemSucesso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel labelCronometro;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelTotalErro;
    private javax.swing.JLabel labelTotalSucesso;
    private javax.swing.JButton pause;
    private javax.swing.JButton resume;
    private javax.swing.JButton stop;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Object object) {

        ListenerImportacaoResponse response = (ListenerImportacaoResponse) object;

        switch (response.getStatusBarraProgresso()) {
            case START: {
                atualizarTela(response);
            }
            break;
            case STOP: {
                atualizarTela(response);
                ativarBotoesControle(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
                timer.cancel();
            }
            break;
        }

    }

    private void atualizarTela(ListenerImportacaoResponse response) {
        atualizarBarraProgresso(response.getValorProgresso());
        atualizarStatusSucesso(response.getValorImportacoesSucesso());
        atualizarStatusErro(response.getValorImportacoesErro());
        atualizarTotalizado(response.getTotalImportacoes());

    }
}
