package com.hibernate.projetoveiculos.view;

import com.hibernate.projetoveiculos.controller.*;
import com.hibernate.projetoveiculos.model.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * JInternalFrame – Cadastro de Veículo (padrão MVC)
 */
public class FrmVeiculo extends javax.swing.JInternalFrame {

    private final VeiculoController controller = new VeiculoController();
    private final TipoVeiculoController tipoVeiculoController = new TipoVeiculoController();
    private final CorController corController = new CorController();
    private final PessoaController pessoaController = new PessoaController();
    private final MunicipioController municipioController = new MunicipioController();

    private Long idSelecionado = null;

    public FrmVeiculo() {
        initComponents();
        carregarCombos();
        listarVeiculos();
        tblDados.getSelectionModel().addListSelectionListener(e -> preencherCampos());
    }

    private void carregarCombos() {
        cbMunicipio.removeAllItems();
        for (Municipio m : municipioController.listar()) cbMunicipio.addItem(m);

        cbCor.removeAllItems();
        for (Cor c : corController.listar()) cbCor.addItem(c);

        cbTipoVeiculo.removeAllItems();
        for (TipoVeiculo t : tipoVeiculoController.listar()) cbTipoVeiculo.addItem(t);

        cbProprietario.removeAllItems();
        cbMotorista.removeAllItems();
        for (Pessoa p : pessoaController.listar()) {
            cbProprietario.addItem(p);
            cbMotorista.addItem(p);
        }
    }

    private void listarVeiculos() {
        List<Veiculo> lista = controller.listar();
        DefaultTableModel dtm = (DefaultTableModel) tblDados.getModel();
        dtm.setRowCount(0);
        for (Veiculo v : lista) {
            dtm.addRow(new Object[]{
                v.getId(),
                v.getPlaca(),
                v.getStatus(),
                v.getMunicipio() != null ? v.getMunicipio().getNome() : "",
                v.getUfPlaca(),
                v.getMarca(),
                v.getModelo(),
                v.getAnoFabricacao(),
                v.getAnoModelo(),
                v.getCor() != null ? v.getCor().getDescricao() : "",
                v.getChassi(),
                v.getRenavam(),
                v.getTipoVeiculo() != null ? v.getTipoVeiculo().getDescricao() : "",
                v.getProprietario() != null ? v.getProprietario().getId() : "",
                v.getProprietario() != null ? v.getProprietario().getNome() : "",
                v.getMotorista() != null ? v.getMotorista().getId() : "",
                v.getMotorista() != null ? v.getMotorista().getNome() : ""
            });
        }
        limparCampos();
    }

    private void salvarVeiculo() {
        try {
            Veiculo v = new Veiculo();
            if (idSelecionado != null) v.setId(idSelecionado);

            v.setPlaca(txtPlaca.getText().trim().toUpperCase());
            v.setStatus(txtStatus.getText().trim());
            v.setUfPlaca(txtUF.getText().trim().toUpperCase());
            v.setMunicipio((Municipio) cbMunicipio.getSelectedItem());
            v.setMarca(txtMarca.getText().trim());
            v.setModelo(txtModelo.getText().trim());
            v.setAnoFabricacao(Integer.parseInt(txtAnoFab.getText().trim()));
            v.setAnoModelo(Integer.parseInt(txtAnoMod.getText().trim()));
            v.setCor((Cor) cbCor.getSelectedItem());
            v.setChassi(txtChassi.getText().trim().toUpperCase());
            v.setRenavam(txtRenavam.getText().trim());
            v.setTipoVeiculo((TipoVeiculo) cbTipoVeiculo.getSelectedItem());
            v.setProprietario((Pessoa) cbProprietario.getSelectedItem());
            v.setMotorista((Pessoa) cbMotorista.getSelectedItem());

            controller.salvar(v);
            listarVeiculos();
            JOptionPane.showMessageDialog(this, "Veículo salvo com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar veículo: " + ex.getMessage());
        }
    }

    private void editarVeiculo() {
        int row = tblDados.getSelectedRow();
        if (row >= 0) {
            idSelecionado = (Long) tblDados.getValueAt(row, 0);
            txtPlaca.setText((String) tblDados.getValueAt(row, 1));
            txtStatus.setText((String) tblDados.getValueAt(row, 2));
            // Buscar entidades pelo nome ou ID se desejar preencher combos (pode ajustar conforme seus métodos)
            // Aqui está exemplo básico, pode melhorar para garantir seleção correta no combo
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para editar.");
        }
    }

    private void excluirVeiculo() {
        int row = tblDados.getSelectedRow();
        if (row >= 0) {
            Long id = (Long) tblDados.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Confirma a exclusão do veículo selecionado?", "Excluir", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                controller.excluir(id);
                listarVeiculos();
                JOptionPane.showMessageDialog(this, "Veículo excluído.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para excluir.");
        }
    }

    private void limparCampos() {
        idSelecionado = null;
        txtPlaca.setText("");
        txtStatus.setText("");
        txtUF.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtAnoFab.setText("");
        txtAnoMod.setText("");
        txtChassi.setText("");
        txtRenavam.setText("");
        cbMunicipio.setSelectedIndex(-1);
        cbCor.setSelectedIndex(-1);
        cbTipoVeiculo.setSelectedIndex(-1);
        cbProprietario.setSelectedIndex(-1);
        cbMotorista.setSelectedIndex(-1);
        tblDados.clearSelection();
    }

    private void preencherCampos() {
        int row = tblDados.getSelectedRow();
        if (row >= 0) {
            idSelecionado = (Long) tblDados.getValueAt(row, 0);
            txtPlaca.setText((String) tblDados.getValueAt(row, 1));
            txtStatus.setText((String) tblDados.getValueAt(row, 2));
            // Preencher os demais campos (similar a editarVeiculo)
        }
    }

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
        cbMunicipio         = new javax.swing.JComboBox<>();
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
        cbCor               = new javax.swing.JComboBox<>();

        lblChassi           = new javax.swing.JLabel();
        txtChassi           = new javax.swing.JTextField();
        lblRenavam          = new javax.swing.JLabel();
        txtRenavam          = new javax.swing.JTextField();
        lblTipoVeiculo      = new javax.swing.JLabel();
        cbTipoVeiculo       = new javax.swing.JComboBox<>();

        lblProprietario     = new javax.swing.JLabel();
        cbProprietario      = new javax.swing.JComboBox<>();
        lblMotorista        = new javax.swing.JLabel();
        cbMotorista         = new javax.swing.JComboBox<>();

        btnSalvar           = new javax.swing.JButton();
        btnEditar           = new javax.swing.JButton();
        btnExcluir          = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Veículo");
        setPreferredSize(new java.awt.Dimension(950, 350));

        tblDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Placa", "Status", "Município", "UF", "Marca", "Modelo",
                "Ano Fab.", "Ano Mod.", "Cor", "Chassi", "Renavam",
                "Tipo", "ID Prop.", "Nome Prop.", "ID Mot.", "Nome Mot."
            }
        ) {
            public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Long.class : String.class;
            }
        });
        jScrollPane1.setViewportView(tblDados);

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
        lblProprietario.setText("Proprietário*:");
        lblMotorista.setText("Motorista*:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(evt -> salvarVeiculo());

        btnEditar.setText("Editar");
        btnEditar.addActionListener(evt -> editarVeiculo());

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(evt -> excluirVeiculo());

        javax.swing.GroupLayout painelFormLayout = new javax.swing.GroupLayout(painelForm);
        painelForm.setLayout(painelFormLayout);

        painelFormLayout.setHorizontalGroup(
            painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFormLayout.createSequentialGroup().addContainerGap()
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelFormLayout.createSequentialGroup()
                        .addComponent(lblPlaca).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPlaca, 80, 80, 80)
                        .addGap(10)
                        .addComponent(lblStatus).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStatus, 80, 80, 80)
                        .addGap(10)
                        .addComponent(lblMunicipio).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMunicipio, 120, 120, 120)
                        .addGap(10)
                        .addComponent(lblUF).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUF, 30, 30, 30)
                        .addGap(10)
                        .addComponent(lblCor).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCor, 80, 80, 80))
                    .addGroup(painelFormLayout.createSequentialGroup()
                        .addComponent(lblMarca).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMarca, 100, 100, 100)
                        .addGap(10)
                        .addComponent(lblModelo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtModelo, 120, 120, 120)
                        .addGap(10)
                        .addComponent(lblAnoFab).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnoFab, 60, 60, 60)
                        .addGap(10)
                        .addComponent(lblAnoMod).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnoMod, 60, 60, 60))
                    .addGroup(painelFormLayout.createSequentialGroup()
                        .addComponent(lblChassi).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChassi, 180, 180, 180)
                        .addGap(10)
                        .addComponent(lblRenavam).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRenavam, 100, 100, 100)
                        .addGap(10)
                        .addComponent(lblTipoVeiculo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipoVeiculo, 120, 120, 120))
                    .addGroup(painelFormLayout.createSequentialGroup()
                        .addComponent(lblProprietario).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbProprietario, 160, 160, 160)
                        .addGap(10)
                        .addComponent(lblMotorista).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMotorista, 160, 160, 160))
                    .addGroup(painelFormLayout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addGap(10)
                        .addComponent(btnEditar)
                        .addGap(10)
                        .addComponent(btnExcluir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelFormLayout.setVerticalGroup(
            painelFormLayout.createSequentialGroup().addContainerGap()
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlaca)  .addComponent(txtPlaca)
                    .addComponent(lblStatus) .addComponent(txtStatus)
                    .addComponent(lblMunicipio) .addComponent(cbMunicipio)
                    .addComponent(lblUF)  .addComponent(txtUF)
                    .addComponent(lblCor) .addComponent(cbCor))
                .addGap(8)
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarca) .addComponent(txtMarca)
                    .addComponent(lblModelo).addComponent(txtModelo)
                    .addComponent(lblAnoFab).addComponent(txtAnoFab)
                    .addComponent(lblAnoMod).addComponent(txtAnoMod))
                .addGap(8)
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChassi) .addComponent(txtChassi)
                    .addComponent(lblRenavam).addComponent(txtRenavam)
                    .addComponent(lblTipoVeiculo).addComponent(cbTipoVeiculo))
                .addGap(8)
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProprietario).addComponent(cbProprietario)
                    .addComponent(lblMotorista).addComponent(cbMotorista))
                .addGap(12)
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    // ==== Declaração de variáveis ==== //
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDados;
    private javax.swing.JPanel painelForm;

    private javax.swing.JLabel lblPlaca, lblStatus, lblMunicipio, lblUF, lblMarca, lblModelo,
            lblAnoFab, lblAnoMod, lblCor, lblChassi, lblRenavam, lblTipoVeiculo,
            lblProprietario, lblMotorista;

    private javax.swing.JTextField txtPlaca, txtStatus, txtUF, txtMarca, txtModelo,
            txtAnoFab, txtAnoMod, txtChassi, txtRenavam;

    private javax.swing.JComboBox<Municipio> cbMunicipio;
    private javax.swing.JComboBox<Cor> cbCor;
    private javax.swing.JComboBox<TipoVeiculo> cbTipoVeiculo;
    private javax.swing.JComboBox<Pessoa> cbProprietario;
    private javax.swing.JComboBox<Pessoa> cbMotorista;
}
