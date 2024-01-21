/**
 * 
 */
package com.gestetud.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import java.sql.PreparedStatement;

/**
 * 
 */
@RequestScoped
@Named
public class EtudiantBean {
	private Etudiant etudiant;
	private List<Etudiant> listeEtudiant;
	
	public EtudiantBean() {
		//instancier classe Etd
		etudiant = new Etudiant();
	}
	//methode pour ajouter un étudiant
	public String addEtudiant(Etudiant e) {
		//choisir notre driver  mysql
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//connexion à la base de donnée
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdcrudjsf", "root", "");
			//definir la requette
			String req = "insert into etudiant (nom, prenom, datenaiss) value (?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(req);
			//renseigner les requetes
			ps.setString(1, e.getNom());
			ps.setString(2, e.getPrenom());
			ps.setString(3, e.getDatenaiss());
			
			//execution de la requete
			
			ps.execute();
			
			etudiant = new Etudiant();
			
			
			return "liste";
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "";
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "";
		}
	}
	
	//mise à jour des information d'un étudiant
	public String updateEtudiant(int etudiantId) {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdcrudjsf", "root", "");
	        String query = "SELECT * FROM etudiant WHERE id = ?";
	        
	        try (PreparedStatement ps = con.prepareStatement(query)) {
	            ps.setInt(1, etudiantId);
	            ResultSet rs = ps.executeQuery();
	            
	            if (rs.next()) {
	                etudiant.setId(rs.getInt("id"));
	                etudiant.setNom(rs.getString("nom"));
	                etudiant.setPrenom(rs.getString("prenom"));
	                etudiant.setDatenaiss(rs.getString("datenaiss"));
	            }
	        }
	        
	        return "modifier"; // Redirige vers une page où vous pouvez afficher/modifier les données de l'étudiant
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        // Gérer l'exception de manière appropriée dans une application réelle
	        return ""; // ou rediriger vers une page d'erreur
	    }
	}

    public String saveEtudiant() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdcrudjsf", "root", "");
            String query = "UPDATE etudiant SET nom = ?, prenom = ?, datenaiss = ? WHERE id = ?";

            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, etudiant.getNom());
                ps.setString(2, etudiant.getPrenom());
                ps.setString(3, etudiant.getDatenaiss());
                ps.setInt(4, etudiant.getId());

                ps.executeUpdate();
            }

            // Réinitialiser l'objet Etudiant après la modification
            etudiant = new Etudiant();

            // Mettre à jour la liste des étudiants après la modification
            chargerListeEtudiant();

            return "liste"; // Redirige vers la liste après la modification
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée dans une application réelle
            return ""; // ou rediriger vers une page d'erreur
        }
    }

    //Suppression d'un étudiant
	public void deleteEtudiant(int etudiantId) {
	    try {
	        System.out.println("Delete called for student ID: " + etudiantId);  // Ajout de cette ligne
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdcrudjsf", "root", "");
	        String query = "DELETE FROM etudiant WHERE id = ?";
	        
	        try (PreparedStatement ps = con.prepareStatement(query)) {
	            ps.setInt(1, etudiantId);
	            ps.executeUpdate();
	        }
	        
	        // Mettre à jour la liste des étudiants après la suppression
	        chargerListeEtudiant();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        // Gérer l'exception de manière appropriée dans une application réelle
	    }
	}

	public void chargerListeEtudiant() {
		listeEtudiant = new ArrayList<Etudiant>();
		
		try {
			//choisir notre driver  mysql
			Class.forName("com.mysql.jdbc.Driver");
			
			//connexion à la base de donnée
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdcrudjsf", "root", "");
			
			//requete preparer pour éviter les attaques par injection sql
			PreparedStatement ps = con.prepareStatement("select * from etudiant");
			
			//execution
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Etudiant e = new Etudiant();
				e.setId(rs.getInt("id"));
				e.setNom(rs.getString("nom"));
				e.setPrenom(rs.getString("prenom"));
				e.setDatenaiss(rs.getString("datenaiss"));
				
				//ajout de l'étudiant
				listeEtudiant.add(e);
			}
			}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the etudiant
	 */
	public Etudiant getEtudiant() {
		return etudiant;
	}

	/**
	 * @param etudiant the etudiant to set
	 */
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	/**
	 * @return the listeEtudaint
	 */
	public List<Etudiant> getListeEtudiant() {
		return listeEtudiant;
	}

	/**
	 * @param listeEtudaint the listeEtudaint to set
	 */
	public void setListeEtudiant(List<Etudiant> listeEtudiant) {
		this.listeEtudiant = listeEtudiant;
	}
	
}
