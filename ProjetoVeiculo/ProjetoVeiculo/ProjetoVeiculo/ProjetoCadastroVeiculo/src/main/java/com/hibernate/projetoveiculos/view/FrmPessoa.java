package com.hibernate.projetoveiculos.view;

import com.hibernate.projetoveiculos.controller.*;
import com.hibernate.projetoveiculos.model.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 * JInternalFrame – Cadastro de Motorista / Proprietário.
 */
public class FrmPessoa extends javax.swing.JInternalFrame {

    private final PessoaController controller = new PessoaController();
    private final MunicipioController municipioController = new MunicipioController();
    private Pessoa pessoaSelecionada = null;

    public FrmPessoa() {
        initComponents();
        carregarMunicipios();
        configurarMascaraDocumento();
        listar();

        cbTipoPessoa.addActionListener(e -> configurarMascaraDocumento());
    }

    private void carregarMunicipios() {
        cbMunicipio.removeAllItems();
        for (Municipio m : municipioController.listar()) {
            cbMunicipio.addItem(m);
        }
    }

    private void listar() {
        List<Pessoa> lista = controller.listar();
        DefaultTableModel dtm = (DefaultTableModel) tblDados.getModel();
        dtm.setRowCount(0);
        for (Pessoa p : lista) {
            String doc = (p.getTipo() != null && p.getTipo().equals("J")) ? formatarCNPJ(p.getCnpj()) : formatarCPF(p.getCpf());
            String tipo = (p.getTipo() != null && p.getTipo().equals("J")) ? "Jurídica" : "Física";
            dtm.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                tipo,
                doc,
                p.getMunicipio() != null ? p.getMunicipio().getNome() : ""
            });
        }
        limparCampos();
        pessoaSelecionada = null;
    }

    private void limparCampos() {
        txtNome.setText("");
        cbTipoPessoa.setSelectedIndex(0);
        txtDocumento.setValue(null);
        cbMunicipio.setSelectedIndex(-1);
        btnSalvar.setEnabled(true);
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }

    private void preencherCamposParaEdicao(Pessoa p) {
        txtNome.setText(p.getNome());
        cbTipoPessoa.setSelectedItem(p.getTipo() != null && p.getTipo().equals("J") ? "Jurídica" : "Física");
        configurarMascaraDocumento();
        if (p.getTipo() != null && p.getTipo().equals("J")) {
            txtDocumento.setText(apenasNumeros(p.getCnpj()));
        } else {
            txtDocumento.setText(apenasNumeros(p.getCpf()));
        }
        cbMunicipio.setSelectedItem(p.getMunicipio());
        btnSalvar.setEnabled(false);
        btnEditar.setEnabled(true);
        btnExcluir.setEnabled(true);
    }

    private void salvar() {
        try {
            Pessoa p = new Pessoa();
            p.setNome(txtNome.getText().trim());
            String tipo = cbTipoPessoa.getSelectedItem().toString();
            if (tipo.equals("Jurídica")) {
                p.setTipo("J");
                p.setCnpj(apenasNumeros(txtDocumento.getText()));
                p.setCpf(null);
            } else {
                p.setTipo("F");
                p.setCpf(apenasNumeros(txtDocumento.getText()));
                p.setCnpj(null);
            }

            Municipio municSelecionado = (Municipio) cbMunicipio.getSelectedItem();
            if (municSelecionado != null) {
                Municipio municGerenciado = municipioController.buscarPorId(municSelecionado.getId());
                p.setMunicipio(municGerenciado);
            } else {
                p.setMunicipio(null);
            }

            controller.salvar(p);
            listar();
            JOptionPane.showMessageDialog(this, "Registro salvo.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }

    private void editar() {
        if (pessoaSelecionada == null) return;
        try {
            pessoaSelecionada.setNome(txtNome.getText().trim());
            String tipo = cbTipoPessoa.getSelectedItem().toString();
            if (tipo.equals("Jurídica")) {
                pessoaSelecionada.setTipo("J");
                pessoaSelecionada.setCnpj(apenasNumeros(txtDocumento.getText()));
                pessoaSelecionada.setCpf(null);
            } else {
                pessoaSelecionada.setTipo("F");
                pessoaSelecionada.setCpf(apenasNumeros(txtDocumento.getText()));
                pessoaSelecionada.setCnpj(null);
            }

            Municipio municSelecionado = (Municipio) cbMunicipio.getSelectedItem();
            if (municSelecionado != null) {
                Municipio municGerenciado = municipioController.buscarPorId(municSelecionado.getId());
                pessoaSelecionada.setMunicipio(municGerenciado);
            } else {
                pessoaSelecionada.setMunicipio(null);
            }

            controller.atualizar(pessoaSelecionada);
            listar();
            JOptionPane.showMessageDialog(this, "Registro atualizado.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
        }
    }

    private void excluir() {
        if (pessoaSelecionada == null) return;
        int confirm = JOptionPane.showConfirmDialog(this, "Confirma a exclusão da pessoa selecionada?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                controller.excluir(pessoaSelecionada);
                listar();
                JOptionPane.showMessageDialog(this, "Registro excluído.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
            }
        }
    }

    // Instala a máscara de CPF ou CNPJ de acordo com o tipo selecionado
    private void configurarMascaraDocumento() {
        try {
            String tipo = cbTipoPessoa.getSelectedItem().toString();
            MaskFormatter mask;
            if (tipo.equals("Jurídica")) {
                mask = new MaskFormatter("##.###.###/####-##");
            } else {
                mask = new MaskFormatter("###.###.###-##");
            }
            mask.setPlaceholderCharacter('_');
            txtDocumento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));
            txtDocumento.setValue(null);
        } catch (Exception ex) {
            // Ignora erros de máscara
        }
    }

    // Remove caracteres não numéricos
    private String apenasNumeros(String texto) {
        if (texto == null) return "";
        return texto.replaceAll("[^0-9]", "");
    }

    // Formata CNPJ para exibição (quando estiver sem máscara)
    private String formatarCNPJ(String cnpj) {
        String num = apenasNumeros(cnpj);
        if (num.length() == 14)
            return num.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        return cnpj != null ? cnpj : "";
    }

    // Formata CPF para exibição (quando estiver sem máscara)
    private String formatarCPF(String cpf) {
        String num = apenasNumeros(cpf);
        if (num.length() == 11)
            return num.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        return cpf != null ? cpf : "";
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDados = new javax.swing.JTable();
        painelForm = new javax.swing.JPanel();

        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblTipoPessoa = new javax.swing.JLabel();
        cbTipoPessoa = new javax.swing.JComboBox<>();
        lblDocumento = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JFormattedTextField(); // Usando JFormattedTextField!
        lblMunicipio = new javax.swing.JLabel();
        cbMunicipio = new javax.swing.JComboBox<>();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Motorista / Proprietário");

        tblDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "ID", "Nome", "Tipo", "CPF/CNPJ", "Cidade" }
        ) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        tblDados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblDados.getSelectionModel().addListSelectionListener(e -> {
            int row = tblDados.getSelectedRow();
            if (row >= 0) {
                Long id = (Long) tblDados.getValueAt(row, 0);
                pessoaSelecionada = controller.buscarPorId(id);
                if (pessoaSelecionada != null) {
                    preencherCamposParaEdicao(pessoaSelecionada);
                }
            }
        });
        jScrollPane1.setViewportView(tblDados);

        lblNome.setText("Nome:");
        lblTipoPessoa.setText("Tipo:");
        cbTipoPessoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Física", "Jurídica" }));
        lblDocumento.setText("CPF/CNPJ:");
        lblMunicipio.setText("Município:");

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
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12)
                .addComponent(lblTipoPessoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12)
                .addComponent(lblDocumento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12)
                .addComponent(lblMunicipio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12)
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
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoPessoa)
                    .addComponent(cbTipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDocumento)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMunicipio)
                    .addComponent(cbMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JButton btnSalvar, btnEditar, btnExcluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNome, lblTipoPessoa, lblDocumento, lblMunicipio;
    private javax.swing.JPanel painelForm;
    private javax.swing.JTable tblDados;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtDocumento;
    private javax.swing.JComboBox<String> cbTipoPessoa;
    private javax.swing.JComboBox<Municipio> cbMunicipio;
    // End of variables declaration
}
