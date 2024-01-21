/**
 * 
 */
package com.gestetud.bean;

/**
 * 
 */
public class Etudiant {
	private int id;
	private String nom;
	private String prenom;
	private String datenaiss;
	
	public Etudiant() {
		
	}

	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param datenaiss 
	
	 */
	public Etudiant(int id, String nom, String prenom, String datenaiss) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.datenaiss = datenaiss;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the datenaiss
	 */
	public String getDatenaiss() {
		return datenaiss;
	}

	/**
	 * @param datenaiss the datenaiss to set
	 */
	public void setDatenaiss(String datenaiss) {
		this.datenaiss = datenaiss;
	}
	
	
	
}
