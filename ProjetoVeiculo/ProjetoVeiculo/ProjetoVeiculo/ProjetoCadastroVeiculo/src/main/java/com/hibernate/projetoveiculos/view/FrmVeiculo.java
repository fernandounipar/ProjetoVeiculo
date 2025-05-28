package com.hibernate.projetoveiculos.view;

import com.hibernate.projetoveiculos.controller.VeiculoController;
import com.hibernate.projetoveiculos.model.Veiculo;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * JInternalFrame – Cadastro de Veículo.
 * Estrutura copiada do FrmClientes e ampliada para todos os campos de Veículo.
 */
public class FrmVeiculo extends javax.swing.JInternalFrame {

    private final VeiculoController controller = new VeiculoController();

    public FrmVeiculo() {
        initComponents();
        listarVeiculos();
    }

    /* ======================= CRUD ======================= */
    private void listarVeiculos() {
        List<Veiculo> lista = controller.listar();
        DefaultTableModel dtm = (DefaultTableModel) tblDados.getModel();
        dtm.setRowCount(0);
        for (Veiculo v : lista) {
            dtm.addRow(new Object[]{
                v.getPlaca(),
                v.getStatus(),
                v.getMunicipio().getNome(),
                v.getUfPlaca(),
                v.getMarca(),
                v.getModelo(),
                v.getAnoFabricacao(),
                v.getAnoModelo(),
                v.getCor(),
                v.getChassi(),
                v.getRenavam(),
                v.getTipoVeiculo().getDescricao(),
                v.getProprietario().getId(),
                v.getProprietario().getNome(),
                v.getMotorista().getId(),
                v.getMotorista().getNome()
            });
        }
    }

    private void salvarVeiculo() {
        try {
            Veiculo v = new Veiculo();

            v.setPlaca(txtPlaca.getText().trim().toUpperCase());
            v.setStatus(txtStatus.getText().trim());
            v.setUfPlaca(txtUF.getText().trim().toUpperCase());
            v.setMunicipioId(Long.parseLong(txtMunicipio.getText().trim())); // ajuste se for nome
            v.setMarca(txtMarca.getText().trim());
            v.setModelo(txtModelo.getText().trim());
            v.setAnoFabricacao(Integer.parseInt(txtAnoFab.getText().trim()));
            v.setAnoModelo(Integer.parseInt(txtAnoMod.getText().trim()));
            v.setCor(txtCor.getText().trim());
            v.setChassi(txtChassi.getText().trim().toUpperCase());
            v.setRenavam(txtRenavam.getText().trim());
            v.setTipoVeiculoId(Long.parseLong(txtTipoVeiculo.getText().trim()));

            v.setProprietarioId(Long.parseLong(txtIdProprietario.getText().trim()));
            v.setProprietarioNome(txtNomeProprietario.getText().trim());

            v.setMotoristaId(Long.parseLong(txtIdMotorista.getText().trim()));
            v.setMotoristaNome(txtNomeMotorista.getText().trim());

            controller.salvar(v);
            listarVeiculos();
            JOptionPane.showMessageDialog(this, "Veículo salvo com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    /* ======================= INIT COMPONENTS ======================= */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1        = new javax.swing.JScrollPane();
        tblDados            = new javax.swing.JTable();
        painelForm          = new javax.swing.JPanel();

        lblPlaca            = new javax.swing.JLabel();
        txtPlaca            = new javax.swing.JTextField();
        lblStatus           = new javax.swing.JLabel();
        txtStatus           = new javax.swing.JTextField();
        lblMunicipio        = new javax.swing.JLabel();
        txtMunicipio        = new javax.swing.JTextField();
        lblUF               = new javax.swing.JLabel();
        txtUF               = new javax.swing.JTextField();

        lblMarca            = new javax.swing.JLabel();
        txtMarca            = new javax.swing.JTextField();
        lblModelo           = new javax.swing.JLabel();
        txtModelo           = new javax.swing.JTextField();
        lblAnoFab           = new javax.swing.JLabel();
        txtAnoFab           = new javax.swing.JTextField();
        lblAnoMod           = new javax.swing.JLabel();
        txtAnoMod           = new javax.swing.JTextField();
        lblCor              = new javax.swing.JLabel();
        txtCor              = new javax.swing.JTextField();

        lblChassi           = new javax.swing.JLabel();
        txtChassi           = new javax.swing.JTextField();
        lblRenavam          = new javax.swing.JLabel();
        txtRenavam          = new javax.swing.JTextField();
        lblTipoVeiculo      = new javax.swing.JLabel();
        txtTipoVeiculo      = new javax.swing.JTextField();

        lblIdProprietario   = new javax.swing.JLabel();
        txtIdProprietario   = new javax.swing.JTextField();
        lblNomeProprietario = new javax.swing.JLabel();
        txtNomeProprietario = new javax.swing.JTextField();

        lblIdMotorista      = new javax.swing.JLabel();
        txtIdMotorista      = new javax.swing.JTextField();
        lblNomeMotorista    = new javax.swing.JLabel();
        txtNomeMotorista    = new javax.swing.JTextField();

        btnSalvar           = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Veículo");

        tblDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Placa", "Status", "Município", "UF", "Marca", "Modelo",
                "Ano Fab.", "Ano Mod.", "Cor", "Chassi", "Renavam",
                "Tipo", "ID Prop.", "Nome Prop.", "ID Mot.", "Nome Mot."
            }
        ) {
            public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }
        });
        jScrollPane1.setViewportView(tblDados);

        /* ===== Labels ===== */
        lblPlaca.setText("Placa*:");
        lblStatus.setText("Status:");
        lblMunicipio.setText("Município*:");
        lblUF.setText("UF:");
        lblMarca.setText("Marca*:");
        lblModelo.setText("Modelo*:");
        lblAnoFab.setText("Ano Fab.:");
        lblAnoMod.setText("Ano Mod.:");
        lblCor.setText("Cor*:");
        lblChassi.setText("Número Chassi:");
        lblRenavam.setText("Renavam*:");
        lblTipoVeiculo.setText("Tipo Veículo*:");
        lblIdProprietario.setText("ID Proprietário*:");
        lblNomeProprietario.setText("Nome Proprietário*:");
        lblIdMotorista.setText("ID Motorista*:");
        lblNomeMotorista.setText("Nome Motorista*:");

        /* ===== Botão ===== */
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(evt -> salvarVeiculo());

        /* ===== Layout ===== */
        javax.swing.GroupLayout painelFormLayout = new javax.swing.GroupLayout(painelForm);
        painelForm.setLayout(painelFormLayout);

        painelFormLayout.setHorizontalGroup(
            painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFormLayout.createSequentialGroup().addContainerGap()
                /* ---- 1ª Linha ---- */
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelFormLayout.createSequentialGroup()
                        .addComponent(lblPlaca).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPlaca, 80, 80, 80)
                        .addGap(18)
                        .addComponent(lblStatus).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStatus, 80, 80, 80)
                        .addGap(18)
                        .addComponent(lblMunicipio).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMunicipio, 120, 120, 120)
                        .addGap(18)
                        .addComponent(lblUF).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUF, 30, 30, 30))
                    /* ---- 2ª Linha ---- */
                    .addGroup(painelFormLayout.createSequentialGroup()
                        .addComponent(lblMarca).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMarca, 100, 100, 100)
                        .addGap(18)
                        .addComponent(lblModelo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtModelo, 120, 120, 120)
                        .addGap(18)
                        .addComponent(lblAnoFab).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnoFab, 60, 60, 60)
                        .addGap(18)
                        .addComponent(lblAnoMod).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnoMod, 60, 60, 60)
                        .addGap(18)
                        .addComponent(lblCor).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCor, 80, 80, 80))
                    /* ---- 3ª Linha ---- */
                    .addGroup(painelFormLayout.createSequentialGroup()
                        .addComponent(lblChassi).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChassi, 180, 180, 180)
                        .addGap(18)
                        .addComponent(lblRenavam).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRenavam, 100, 100, 100)
                        .addGap(18)
                        .addComponent(lblTipoVeiculo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTipoVeiculo, 80, 80, 80))
                    /* ---- 4ª Linha ---- */
                    .addGroup(painelFormLayout.createSequentialGroup()
                        .addComponent(lblIdProprietario).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdProprietario, 60, 60, 60)
                        .addGap(18)
                        .addComponent(lblNomeProprietario).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeProprietario, 160, 160, 160)
                        .addGap(18)
                        .addComponent(lblIdMotorista).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdMotorista, 60, 60, 60)
                        .addGap(18)
                        .addComponent(lblNomeMotorista).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeMotorista, 160, 160, 160))
                    /* ---- Botão ---- */
                    .addComponent(btnSalvar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelFormLayout.setVerticalGroup(
            painelFormLayout.createSequentialGroup().addContainerGap()
                /* 1ª linha */
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlaca)  .addComponent(txtPlaca)
                    .addComponent(lblStatus) .addComponent(txtStatus)
                    .addComponent(lblMunicipio) .addComponent(txtMunicipio)
                    .addComponent(lblUF)  .addComponent(txtUF))
                .addGap(8)
                /* 2ª linha */
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarca) .addComponent(txtMarca)
                    .addComponent(lblModelo).addComponent(txtModelo)
                    .addComponent(lblAnoFab).addComponent(txtAnoFab)
                    .addComponent(lblAnoMod).addComponent(txtAnoMod)
                    .addComponent(lblCor)   .addComponent(txtCor))
                .addGap(8)
                /* 3ª linha */
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChassi) .addComponent(txtChassi)
                    .addComponent(lblRenavam).addComponent(txtRenavam)
                    .addComponent(lblTipoVeiculo).addComponent(txtTipoVeiculo))
                .addGap(8)
                /* 4ª linha */
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdProprietario).addComponent(txtIdProprietario)
                    .addComponent(lblNomeProprietario).addComponent(txtNomeProprietario)
                    .addComponent(lblIdMotorista).addComponent(txtIdMotorista)
                    .addComponent(lblNomeMotorista).addComponent(txtNomeMotorista))
                .addGap(12)
                .addComponent(btnSalvar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        /* ===== Frame Layout ===== */
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(painelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(painelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    /* ========== Declaração de Variáveis ========== */
    private javax.swing.JButton btnSalvar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDados;
    private javax.swing.JPanel painelForm;

    private javax.swing.JLabel lblPlaca, lblStatus, lblMunicipio, lblUF, lblMarca, lblModelo,
                               lblAnoFab, lblAnoMod, lblCor, lblChassi, lblRenavam, lblTipoVeiculo,
                               lblIdProprietario, lblNomeProprietario, lblIdMotorista, lblNomeMotorista;

    private javax.swing.JTextField txtPlaca, txtStatus, txtMunicipio, txtUF, txtMarca, txtModelo,
                                   txtAnoFab, txtAnoMod, txtCor, txtChassi, txtRenavam, txtTipoVeiculo,
                                   txtIdProprietario, txtNomeProprietario, txtIdMotorista, txtNomeMotorista;
}
