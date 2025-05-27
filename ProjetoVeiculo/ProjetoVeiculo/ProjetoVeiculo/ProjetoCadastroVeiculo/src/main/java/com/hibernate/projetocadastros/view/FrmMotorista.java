package com.hibernate.projetocadastros.view;

import com.hibernate.projetocadastros.controller.MotoristaController;
import com.hibernate.projetocadastros.model.Motorista;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * JInternalFrame – Cadastro de Motorista / Proprietário.
 */
public class FrmMotorista extends javax.swing.JInternalFrame {

    private final MotoristaController controller = new MotoristaController();

    public FrmMotorista() {
        initComponents();
        listar();
    }

    private void listar() {
        List<Motorista> lista = controller.listar();
        DefaultTableModel dtm = (DefaultTableModel) tblDados.getModel();
        dtm.setRowCount(0);
        for (Motorista m : lista) {
            dtm.addRow(new Object[]{m.getNome(), m.getCpf(), m.getMunicipio().getNome()});
        }
    }

    private void salvar() {
        try {
            Motorista m = new Motorista();
            m.setNome(txtNome.getText());
            m.setCpf(txtCpf.getText());
            // TODO: demais campos
            controller.salvar(m);
            listar();
            JOptionPane.showMessageDialog(this, "Registro salvo.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDados = new javax.swing.JTable();
        painelForm = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblCpf = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Motorista / Proprietário");

        tblDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Nome", "CPF", "Cidade" }
        ) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        jScrollPane1.setViewportView(tblDados);

        lblNome.setText("Nome:");
        lblCpf.setText("CPF:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(evt -> salvar());

        javax.swing.GroupLayout painelFormLayout = new javax.swing.GroupLayout(painelForm);
        painelForm.setLayout(painelFormLayout);
        painelFormLayout.setHorizontalGroup(
            painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCpf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelFormLayout.setVerticalGroup(
            painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCpf)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblNome;
    private javax.swing.JPanel painelForm;
    private javax.swing.JTable tblDados;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtNome;
    // End of variables declaration
}
