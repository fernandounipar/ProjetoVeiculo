package com.hibernate.projetoveiculos;

import com.hibernate.projetoveiculos.util.HibernateUtil;
import com.hibernate.projetoveiculos.view.FrmLogin;
import javax.swing.UIManager;
import org.hibernate.Session;

public class ProjetoVeiculos {

    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        try {
            // Aplica o tema Nimbus, se disponível – exatamente como no original
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            // Teste rápido de conexão (abre e fecha a sessão)
            Session session = HibernateUtil.getSession();
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            session.close();

            // Exibe a tela de login
            FrmLogin frmLogin = new FrmLogin();
            frmLogin.setLocationRelativeTo(null);
            frmLogin.setVisible(true);

        } catch (Exception e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}