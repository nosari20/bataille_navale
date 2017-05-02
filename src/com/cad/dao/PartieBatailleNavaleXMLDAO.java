package com.cad.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cad.bataille_navale.bateaux.Bateau;
import com.cad.bataille_navale.bateaux.Coord;
import com.cad.bataille_navale.factory.AncienPartieBatailleNavaleFactory;
import com.cad.bataille_navale.factory.FuturPartieBatailleNavaleFactory;
import com.cad.bataille_navale.factory.ModernePartieBatailleNavaleFactory;
import com.cad.bataille_navale.jeu.BatailleNavale;
import com.cad.bataille_navale.jeu.PartieBatailleNavale;
import com.cad.bataille_navale.mode.Mode;
import com.cad.bataille_navale.mode.ModeNormal;
import com.cad.bataille_navale.mode.ModeTireBateau;
import com.cad.codesUtils.BatailleNavalleJoueurCote;
import com.cad.codesUtils.DAOXmlUtils;
import com.cad.codesUtils.ModePartie;
import com.cad.codesUtils.epoque.Epoque;

public class PartieBatailleNavaleXMLDAO implements PartieBatailleNavaleDao {

	private static PartieBatailleNavaleXMLDAO instance = null;

	private PartieBatailleNavaleXMLDAO() {

	}

	public static PartieBatailleNavaleDao getInstance() {
		if (instance == null)
			instance = new PartieBatailleNavaleXMLDAO();
		return instance;
	}

	@Override
	public void save(PartieBatailleNavale partie) {
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();

			// Root element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(DAOXmlUtils.BATAILLE_NAVALE);
			doc.appendChild(rootElement);

			rootElement.appendChild(saveJoueur(doc, partie.getBateauJ1(),
					partie.getGrille(BatailleNavalleJoueurCote.GAUCHE), DAOXmlUtils.JOUEUR1));
			rootElement.appendChild(saveJoueur(doc, partie.getBateauJ2(),
					partie.getGrille(BatailleNavalleJoueurCote.DROIT), DAOXmlUtils.JOUEUR2));

			Element mode = doc.createElement(DAOXmlUtils.MODE);
			mode.appendChild(doc.createTextNode(modeForDAO(partie.getMode())));
			rootElement.appendChild(mode);
			Element epoque = doc.createElement(DAOXmlUtils.EPOQUE);
			epoque.appendChild(doc.createTextNode(partie.getEpoque().toString()));
			rootElement.appendChild(epoque);
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			String path = System.getProperty("user.dir");
			StreamResult result = new StreamResult(new File(path + "\\" + partie.getNom() + ".xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String modeForDAO(Mode mode) {
		if (mode instanceof ModeNormal)
			return ModePartie.MODE_NORMAL.toString();
		else if (mode instanceof ModeTireBateau)
			return ModePartie.MODE_CHOIX_TIREUR.toString();
		return null;
	}

	private Element saveJoueur(Document doc, List<Bateau> listBateauJ1, int[][] grille, String nom) {
		Element joueur1 = doc.createElement(nom);

		for (Bateau b : listBateauJ1) {
			Element bateau = doc.createElement(DAOXmlUtils.getBateauCasesLabel(b.getLongueur()));
			Attr nbProjectile = doc.createAttribute(DAOXmlUtils.PROJECTILE);
			nbProjectile.setValue(b.getNbProjectile() + "");
			bateau.setAttributeNode(nbProjectile);
			Attr etat = doc.createAttribute(DAOXmlUtils.ETAT);
			etat.setValue(intTabToString(b.getEtat()));
			bateau.setAttributeNode(etat);
			Attr localisation = doc.createAttribute(DAOXmlUtils.LOCALISATIONS);
			localisation.setValue(CoordTabToString(b.getLocalisation()));
			bateau.setAttributeNode(localisation);
			joueur1.appendChild(bateau);
		}

		Element grilles = doc.createElement(DAOXmlUtils.GRILLES);
		for (int i = 0; i < grille.length; i++)
			for (int j = 0; j < grille[0].length; j++) {
				if (grille[i][j] != BatailleNavale.Code.VIDE) {
					Element caseGrille = doc.createElement(DAOXmlUtils.CASE);
					caseGrille.appendChild(doc.createTextNode(grille[i][j] + ""));
					Attr coord = doc.createAttribute(DAOXmlUtils.COORDONNEE);
					coord.setValue(i + DAOXmlUtils.SEPARATEUR_COORD + j);
					caseGrille.setAttributeNode(coord);
					grilles.appendChild(caseGrille);
				}
			}
		joueur1.appendChild(grilles);
		return joueur1;
	}

	private String CoordTabToString(Coord[] tab) {
		String res = "";
		for (int i = 0; i < tab.length; i++) {
			if (i == 0)
				res += tab[i].x + DAOXmlUtils.SEPARATEUR_COORD + tab[i].y;
			else
				res += DAOXmlUtils.SEPARATEUR + "" + tab[i].x + DAOXmlUtils.SEPARATEUR_COORD + tab[i].y;
		}
		return res;
	}

	private String intTabToString(int[] tab) {
		String res = "";
		for (int i = 0; i < tab.length; i++) {
			if (i == 0)
				res += tab[i];
			else
				res += DAOXmlUtils.SEPARATEUR + "" + tab[i];
		}
		return res;
	}

	@Override
	public PartieBatailleNavale load(String url) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		PartieBatailleNavale partieBatailleNavale = null;
		try {
			String res = "";
			String path = System.getProperty("user.dir");
			DocumentBuilder builder = factory.newDocumentBuilder();
			File fileXML = new File(path + "\\" + url);
			Document xml = builder.parse(fileXML);
			Element root = xml.getDocumentElement();
			XPathFactory xpf = XPathFactory.newInstance();
			XPath xPath = xpf.newXPath();

			Mode mode = null;
			String getMode = "//" + DAOXmlUtils.MODE + "/text()";
			res = xPath.compile(getMode).evaluate(xml);
			if (res.equals(ModePartie.MODE_NORMAL.toString()))
				mode = new ModeNormal();
			else if (res.equals(ModePartie.MODE_CHOIX_TIREUR.toString()))
				mode = new ModeTireBateau();

			String getEpoque = "//" + DAOXmlUtils.EPOQUE + "/text()";
			res = xPath.compile(getEpoque).evaluate(xml);
			if (res.equals(Epoque.XX.toString()))
				partieBatailleNavale = (PartieBatailleNavale) new ModernePartieBatailleNavaleFactory()
						.CreatePartie(mode);
			else if (res.equals(Epoque.XXI.toString()))
				partieBatailleNavale = (PartieBatailleNavale) new FuturPartieBatailleNavaleFactory().CreatePartie(mode);
			else
				partieBatailleNavale = (PartieBatailleNavale) new AncienPartieBatailleNavaleFactory()
						.CreatePartie(mode);

			// Chargement joueur1
			List<Bateau> listJ = partieBatailleNavale.getBateauJ1();
			int[][] grilleJ = partieBatailleNavale.getGrille(BatailleNavalleJoueurCote.GAUCHE);
			loadJoueur(xml, xPath, partieBatailleNavale.getBateauJ1(), grilleJ, DAOXmlUtils.JOUEUR1);
			// Chargement joueur2
			listJ = partieBatailleNavale.getBateauJ2();
			grilleJ = partieBatailleNavale.getGrille(BatailleNavalleJoueurCote.DROIT);
			loadJoueur(xml, xPath, partieBatailleNavale.getBateauJ2(), grilleJ, DAOXmlUtils.JOUEUR2);

		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return partieBatailleNavale;
	}

	private void loadJoueur(Document xml, XPath xPath, List<Bateau> listJ, int[][] grilleJ, String joueur)
			throws XPathExpressionException {
		String res;
		// PARTIE Bateau
		for (Bateau b : listJ) {
			String label = DAOXmlUtils.getBateauCasesLabel(b.getLongueur());
			String getEtat = "/" + DAOXmlUtils.BATAILLE_NAVALE + "/" + joueur + "/" + label + "/@" + DAOXmlUtils.ETAT;
			res = xPath.compile(getEtat).evaluate(xml);
			b.setEtat(stringToIntTab(res));
			String getLocalisation = "/" + DAOXmlUtils.BATAILLE_NAVALE + "/" + joueur + "/" + label + "/@"
					+ DAOXmlUtils.LOCALISATIONS;
			res = xPath.compile(getLocalisation).evaluate(xml);
			b.setLocalistion(StringToCoordTab(res));
			String getProjectile = "/" + DAOXmlUtils.BATAILLE_NAVALE + "/" + joueur + "/" + label + "/@"
					+ DAOXmlUtils.PROJECTILE;
			res = xPath.compile(getProjectile).evaluate(xml);
			b.setProjectile(Integer.parseInt(res));
			b.update();
		}

		NodeList cases = (NodeList) xPath.evaluate(
				"/" + DAOXmlUtils.BATAILLE_NAVALE + "/" + joueur + "/" + DAOXmlUtils.GRILLES + "/*", xml,
				XPathConstants.NODESET);

		for (int temp = 0; temp < cases.getLength(); temp++) {
			Node nNode = cases.item(temp);
			// System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				res = eElement.getAttribute(DAOXmlUtils.COORDONNEE);
				String[] parts = res.split(DAOXmlUtils.SEPARATEUR_COORD);
				int x = Integer.parseInt(parts[0]);
				int y = Integer.parseInt(parts[1]);
				int content = Integer.parseInt(eElement.getTextContent());
				grilleJ[x][y] = content;
			}
		}
	}

	private int[] stringToIntTab(String getEtat) {
		String[] parts = getEtat.split(DAOXmlUtils.SEPARATEUR);
		int[] tab = new int[parts.length];
		int i = 0;
		for (String str : parts) {
			tab[i] = Integer.parseInt(str.trim());// Exception in this line
			i++;
		}
		return tab;
	}

	private Coord[] StringToCoordTab(String s) {
		String[] parts = s.split(DAOXmlUtils.SEPARATEUR);
		Coord[] coord = new Coord[parts.length];
		int i = 0;
		for (String str : parts) {
			String[] coordsParts = str.split(DAOXmlUtils.SEPARATEUR_COORD);
			coord[i] = new Coord(Integer.parseInt(coordsParts[0]), Integer.parseInt(coordsParts[1]));
			i++;
		}
		return coord;
	}
}
