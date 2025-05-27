package com.hibernate.projetocadastros.view;

import com.hibernate.projetocadastros.controller.TipoVeiculoController;
import com.hibernate.projetocadastros.model.TipoVeiculo;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * JInternalFrame – Cadastro de Tipo de Veículo.
 */
public class FrmTipoVeiculo extends javax.swing.JInternalFrame {

    private final TipoVeiculoController controller = new TipoVeiculoController();

    public FrmTipoVeiculo() {
        initComponents();
        listar();
    }

    private void listar() {
        List<TipoVeiculo> lista = controller.listar();
        DefaultTableModel dtm = (DefaultTableModel) tblDados.getModel();
        dtm.setRowCount(0);
        for (TipoVeiculo t : lista) {
            dtm.addRow(new Object[]{t.getDescricao(), t.getCategoria()});
        }
    }

    private void salvar() {
        try {
            TipoVeiculo t = new TipoVeiculo();
            t.setDescricao(txtDescricao.getText());
            t.setCategoria(txtCategoria.getText());
            controller.salvar(t);
            listar();
            JOptionPane.showMessageDialog(this, "Tipo de veículo salvo.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDados = new javax.swing.JTable();
        painelForm = new javax.swing.JPanel();
        lblDescricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Tipo de Veículo");

        tblDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Descrição", "Categoria" }
        ) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        jScrollPane1.setViewportView(tblDados);

        lblDescricao.setText("Descrição:");
        lblCategoria.setText("Categoria:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(evt -> salvar());

        javax.swing.GroupLayout painelFormLayout = new javax.swing.GroupLayout(painelForm);
        painelForm.setLayout(painelFormLayout);
        painelFormLayout.setHorizontalGroup(
            painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelFormLayout.setVerticalGroup(
            painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescricao)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategoria)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JPanel painelForm;
    private javax.swing.JTable tblDados;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration
}
