package welley.capstone.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import welley.capstone.entities.Product;

import java.util.HashMap;
import java.util.Map;

@Component
public class SupplierProcessor implements ItemProcessor<Product, Product> {

    private static final Map<String, String> SUPPLIERS = new HashMap<>();

    public SupplierProcessor() {
        SUPPLIERS.put("1", "3M");
        SUPPLIERS.put("2", "Abbott Medical Optics, Inc.");
        SUPPLIERS.put("3", "Abbott Nutrition");
        SUPPLIERS.put("4", "ACH Food Companies");
        SUPPLIERS.put("5", "Acme Smoked Fish Corp.");
        SUPPLIERS.put("6", "Adria Imports Inc");
        SUPPLIERS.put("7", "Advanced Beauty Systems, Inc.");
        SUPPLIERS.put("8", "Ahold USA, Inc.");
        SUPPLIERS.put("9", "Aidells Sausage Co.");
        SUPPLIERS.put("10", "Alberto-Culver USA, Inc.");
        SUPPLIERS.put("11", "Alcon");
        SUPPLIERS.put("12", "B&G Foods");
        SUPPLIERS.put("13", "Bausch & Lomb Pharmaceuticals, Inc.");
        SUPPLIERS.put("14", "Bausch + Lomb");
        SUPPLIERS.put("15", "Bayer HealthCare Consumer Care");
        SUPPLIERS.put("16", "Bendon Publishing International");
        SUPPLIERS.put("17", "BIC USA Inc.");
        SUPPLIERS.put("18", "Bimbo Bakeries USA, Inc.");
        SUPPLIERS.put("19", "C.H. Guenther & Son, Inc.");
        SUPPLIERS.put("20", "Campbell Soup Company");
        SUPPLIERS.put("21", "CCA Industries Inc.");
        SUPPLIERS.put("22", "Chattem, Inc.");
        SUPPLIERS.put("23", "Church & Dwight Co., Inc.");
        SUPPLIERS.put("24", "Colgate-Palmolive Company");
        SUPPLIERS.put("25", "ConAgra Foods Inc.");
        SUPPLIERS.put("26", "Cumberland Packing Corp.");
        SUPPLIERS.put("27", "Daniele, Inc.");
        SUPPLIERS.put("28", "DCI Cheese Company, Inc.");
        SUPPLIERS.put("29", "Dean Foods Company");
        SUPPLIERS.put("30", "Del Monte Pet Products");
        SUPPLIERS.put("31", "Diamond Foods, Inc.");
        SUPPLIERS.put("32", "Dr. Pepper Snapple Group");
        SUPPLIERS.put("33", "Dr. Pepper/Seven Up, Inc.");
        SUPPLIERS.put("34", "Ducere Pharma");
        SUPPLIERS.put("35", "E&J Gallo Winery");
        SUPPLIERS.put("36", "Eastland Food Corporation, Inc.");
        SUPPLIERS.put("37", "Edward & Sons Trading");
        SUPPLIERS.put("38", "Elmer's Products, Inc.");
        SUPPLIERS.put("39", "Energizer Personal Care");
        SUPPLIERS.put("40", "F.M. Brown's Sons Inc.");
        SUPPLIERS.put("41", "Farley's & Sathers Candy Company, Inc.");
        SUPPLIERS.put("42", "Ferrero U.S.A., Incorporated");
        SUPPLIERS.put("43", "Freeman Cosmetics");
        SUPPLIERS.put("44", "Frito-Lay, Inc.");
        SUPPLIERS.put("45", "Funko, LLC");
        SUPPLIERS.put("46", "Garnier Inc.");
        SUPPLIERS.put("47", "General Mills, Inc.");
        SUPPLIERS.put("48", "Glaxosmithkline Consumer Healthcare, Lp.");
        SUPPLIERS.put("49", "Green Mountain Coffee, Inc.");
        SUPPLIERS.put("50", "H.J. Heinz Company");
        SUPPLIERS.put("51", "Hanesbrands Inc");
        SUPPLIERS.put("52", "Hasbro");
        SUPPLIERS.put("53", "Hormel Foods Corporation");
        SUPPLIERS.put("54", "i-Health, Inc.");
        SUPPLIERS.put("55", "Imperial Toy Corporation");
        SUPPLIERS.put("56", "JFC International Inc.");
        SUPPLIERS.put("57", "Johnson & Johnson");
        SUPPLIERS.put("58", "Kashi International Corporation");
        SUPPLIERS.put("59", "Kellogg Company");
        SUPPLIERS.put("60", "Kimberly-Clark Global Sales, LLC.");
        SUPPLIERS.put("61", "Kraft Foods, Inc.");
    }

    @Override
    public Product process(Product product) throws Exception {

        String supplierId = product.getSupplier(); //  returns supplier id from csv
        String supplier = SUPPLIERS.get(supplierId);
        product.setSupplier(supplier);
        return product;
    }
}
