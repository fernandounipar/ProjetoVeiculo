package com.hibernate.projetoveiculos.view;

import com.hibernate.projetoveiculos.controller.MunicipioController;
import com.hibernate.projetoveiculos.model.Municipio;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * JInternalFrame – Cadastro de Município/Cidade.
 */
public class FrmMunicipio extends javax.swing.JInternalFrame {

    private final MunicipioController controller = new MunicipioController();
    private String codigoEdicao = null; // Para edição

    public FrmMunicipio() {
        initComponents();
        listar();
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
        tblDados.getSelectionModel().addListSelectionListener(e -> atualizarBotoes());
    }

    private void atualizarBotoes() {
        boolean selecionado = tblDados.getSelectedRow() >= 0;
        btnEditar.setEnabled(selecionado);
        btnExcluir.setEnabled(selecionado);
    }

    private void listar() {
        List<Municipio> lista = controller.listar();
        DefaultTableModel dtm = (DefaultTableModel) tblDados.getModel();
        dtm.setRowCount(0);
        for (Municipio m : lista) {
            dtm.addRow(new Object[]{m.getCodIbge(), m.getNome(), m.getUf()});
        }
    }

    private void salvar() {
        try {
            Municipio m = new Municipio();
            m.setCodIbge(txtCodigo.getText().trim());
            m.setNome(txtNome.getText().trim());
            m.setUf(txtUf.getText().toUpperCase().trim());

            if (codigoEdicao == null) {
                controller.salvar(m);
                JOptionPane.showMessageDialog(this, "Município salvo.");
            } else {
                controller.atualizar(m);
                JOptionPane.showMessageDialog(this, "Município atualizado.");
                codigoEdicao = null;
            }
            limparCampos();
            listar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void editar() {
        int row = tblDados.getSelectedRow();
        if (row >= 0) {
            String codigo = tblDados.getValueAt(row, 0).toString();
            String nome = tblDados.getValueAt(row, 1).toString();
            String uf = tblDados.getValueAt(row, 2).toString();

            txtCodigo.setText(codigo);
            txtNome.setText(nome);
            txtUf.setText(uf);

            txtCodigo.setEnabled(false);
            codigoEdicao = codigo;
        }
    }

   private void excluir() {
    int row = tblDados.getSelectedRow();
    if (row >= 0) {
        String codigo = tblDados.getValueAt(row, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this, "Confirma a exclusão do município selecionado?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Municipio municipio = controller.buscarPorCodigoIbge(codigo);
            if (municipio != null) {
                controller.excluir(municipio);
                listar();
                JOptionPane.showMessageDialog(this, "Município excluído.");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Município não encontrado.");
            }
        }
    }
}

    private void limparCampos() {
        txtCodigo.setText("");
        txtNome.setText("");
        txtUf.setText("");
        txtCodigo.setEnabled(true);
        tblDados.clearSelection();
        atualizarBotoes();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDados = new javax.swing.JTable();
        painelForm = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblUf = new javax.swing.JLabel();
        txtUf = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Município / Cidade");

        tblDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Cód. IBGE", "Nome", "UF" }
        ) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        jScrollPane1.setViewportView(tblDados);

        lblCodigo.setText("Cód. IBGE:");
        lblNome.setText("Nome:");
        lblUf.setText("UF:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(evt -> salvar());

        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(evt -> editar());

        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(evt -> excluir());

        javax.swing.GroupLayout painelFormLayout = new javax.swing.GroupLayout(painelForm);
        painelForm.setLayout(painelFormLayout);
        painelFormLayout.setHorizontalGroup(
            painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblUf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelFormLayout.setVerticalGroup(
            painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUf)
                    .addComponent(txtUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(painelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    // Variables declaration
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblUf;
    private javax.swing.JPanel painelForm;
    private javax.swing.JTable tblDados;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtUf;
    // End of variables declaration
}
