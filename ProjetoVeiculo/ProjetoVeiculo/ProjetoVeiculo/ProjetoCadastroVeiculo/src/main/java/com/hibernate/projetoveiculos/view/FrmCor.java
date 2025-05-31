package com.hibernate.projetoveiculos.view;

import com.hibernate.projetoveiculos.controller.CorController;
import com.hibernate.projetoveiculos.model.Cor;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * JInternalFrame – Cadastro de Cor.
 */
public class FrmCor extends javax.swing.JInternalFrame {

    private final CorController controller = new CorController();
    private Long idSelecionado = null;

    public FrmCor() {
        initComponents();
        listar();
        tblDados.getSelectionModel().addListSelectionListener(e -> preencherCampos());
    }

    private void listar() {
        List<Cor> lista = controller.listar();
        DefaultTableModel dtm = (DefaultTableModel) tblDados.getModel();
        dtm.setRowCount(0);
        for (Cor c : lista) {
            dtm.addRow(new Object[]{c.getId(), c.getDescricao()});
        }
        limparCampos();
    }

    private void salvar() {
        try {
            Cor c = new Cor();
            if (idSelecionado != null) c.setId(idSelecionado); // Para editar, senão é novo
            c.setDescricao(txtDescricao.getText().trim());
            controller.salvar(c);
            listar();
            JOptionPane.showMessageDialog(this, "Cor salva.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void editar() {
        int row = tblDados.getSelectedRow();
        if (row >= 0) {
            idSelecionado = (Long) tblDados.getValueAt(row, 0);
            txtDescricao.setText((String) tblDados.getValueAt(row, 1));
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma cor para editar.");
        }
    }

    private void excluir() {
        int row = tblDados.getSelectedRow();
        if (row >= 0) {
            Long id = (Long) tblDados.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Confirma a exclusão da cor selecionada?", "Excluir", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                controller.excluir(id);
                listar();
                JOptionPane.showMessageDialog(this, "Cor excluída.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma cor para excluir.");
        }
    }

    private void limparCampos() {
        idSelecionado = null;
        txtDescricao.setText("");
        tblDados.clearSelection();
    }

    private void preencherCampos() {
        int row = tblDados.getSelectedRow();
        if (row >= 0) {
            idSelecionado = (Long) tblDados.getValueAt(row, 0);
            txtDescricao.setText((String) tblDados.getValueAt(row, 1));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDados = new javax.swing.JTable();
        painelForm = new javax.swing.JPanel();
        lblDescricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Cor");
        setPreferredSize(new java.awt.Dimension(550, 320));

        tblDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "ID", "Descrição" }
        ) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Long.class : String.class;
            }
        });
        jScrollPane1.setViewportView(tblDados);

        lblDescricao.setText("Descrição:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(evt -> {
            salvar();
            limparCampos();
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(evt -> editar());

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(evt -> excluir());

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
                .addComponent(btnSalvar)
                .addGap(8, 8, 8)
                .addComponent(btnEditar)
                .addGap(8, 8, 8)
                .addComponent(btnExcluir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelFormLayout.setVerticalGroup(
            painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescricao)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    // Variables declaration
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JPanel painelForm;
    private javax.swing.JTable tblDados;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration
}
