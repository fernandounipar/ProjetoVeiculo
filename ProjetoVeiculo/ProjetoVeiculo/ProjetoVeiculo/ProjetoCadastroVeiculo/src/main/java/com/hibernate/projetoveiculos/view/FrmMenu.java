package com.hibernate.projetoveiculos.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * Menu principal – estrutura baseada no FrmMenu original,
 * acrescido de um painel de navegação lateral (JSplitPane).
 */
@SuppressWarnings("serial")
public class FrmMenu extends javax.swing.JFrame {

    public FrmMenu() {
        initComponents();
        setLocationRelativeTo(null);
        configurarEventos();
    }

    /* ==================== EVENTOS ==================== */
    private void configurarEventos() {

        // 1) Clique simples em "Módulos" – mostra cadastros
        lstModulos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String modulo = lstModulos.getSelectedValue();
                lstCadastros.setVisible("Frota".equals(modulo));
            }
        });

        // 2) Duplo-clique no cadastro → abre janela
        lstCadastros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    abrirCadastro(lstCadastros.getSelectedValue());
                }
            }
        });
    }

    private void abrirCadastro(String cadastro) {
        JInternalFrame frame = null;

        switch (cadastro) {
            case "Veículo" -> frame = new FrmVeiculo();              // você criará depois
            case "Motorista / Proprietário" -> frame = new FrmMotorista();
            case "Município / Cidade" -> frame = new FrmMunicipio();
            case "Cor" -> frame = new FrmCor();
            case "Tipo de Veículo" -> frame = new FrmTipoVeiculo();
            default -> JOptionPane.showMessageDialog(this,
                    "Cadastro não implementado.", "Aviso",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        if (frame != null) {
            desktopPane.add(frame);
            frame.setVisible(true);
            try {
                frame.setSelected(true);
            } catch (java.beans.PropertyVetoException ex) { /* ignore */ }
        }
    }

    /* ==================== CÓDIGO GERADO (GUIBuilder) ==================== */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        navPanel = new javax.swing.JPanel();
        lblModulos = new javax.swing.JLabel();
        lstModulos = new javax.swing.JList<>();
        lstCadastros = new javax.swing.JList<>();
        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        mnuArquivo = new javax.swing.JMenu();
        mniSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Frota");

        splitPane.setDividerLocation(180);

        navPanel.setLayout(new java.awt.BorderLayout());

        lblModulos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblModulos.setText("Módulos");
        navPanel.add(lblModulos, java.awt.BorderLayout.PAGE_START);

        lstModulos.setModel(new javax.swing.AbstractListModel<String>() {
            final String[] strings = { "Frota" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        navPanel.add(new javax.swing.JScrollPane(lstModulos), java.awt.BorderLayout.CENTER);

        lstCadastros.setModel(new javax.swing.AbstractListModel<String>() {
            final String[] strings = { "Veículo", "Motorista / Proprietário", "Município / Cidade", "Cor", "Tipo de Veículo" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstCadastros.setVisible(false);
        navPanel.add(new javax.swing.JScrollPane(lstCadastros), java.awt.BorderLayout.PAGE_END);

        splitPane.setLeftComponent(navPanel);
        splitPane.setRightComponent(desktopPane);

        mnuArquivo.setText("Arquivo");

        mniSair.setText("Sair");
        mniSair.addActionListener(evt -> dispose());
        mnuArquivo.add(mniSair);

        menuBar.add(mnuArquivo);
        setJMenuBar(menuBar);

        getContentPane().add(splitPane, java.awt.BorderLayout.CENTER);
        pack();
        setExtendedState(MAXIMIZED_BOTH);
    }// </editor-fold>

    /* ==================== VARIÁVEIS GERADAS ==================== */
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JLabel lblModulos;
    private javax.swing.JList<String> lstCadastros;
    private javax.swing.JList<String> lstModulos;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mnuArquivo;
    private javax.swing.JMenuItem mniSair;
    private javax.swing.JPanel navPanel;
    private javax.swing.JSplitPane splitPane;
}
