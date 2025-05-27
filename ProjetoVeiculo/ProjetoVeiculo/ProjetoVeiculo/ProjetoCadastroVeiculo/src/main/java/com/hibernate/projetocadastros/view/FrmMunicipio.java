package com.hibernate.projetocadastros.view;

import com.hibernate.projetocadastros.controller.MunicipioController;
import com.hibernate.projetocadastros.model.Municipio;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * JInternalFrame – Cadastro de Município/Cidade.
 */
public class FrmMunicipio extends javax.swing.JInternalFrame {

    private final MunicipioController controller = new MunicipioController();

    public FrmMunicipio() {
        initComponents();
        listar();
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
            m.setCodIbge(txtCodigo.getText());
            m.setNome(txtNome.getText());
            m.setUf(txtUf.getText().toUpperCase());
            controller.salvar(m);
            listar();
            JOptionPane.showMessageDialog(this, "Município salvo.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvar)
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
                    .addComponent(btnSalvar))
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
