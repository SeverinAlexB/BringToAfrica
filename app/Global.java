import exceptions.AfricaException;
import org.mindrot.jbcrypt.BCrypt;
import play.*;
import models.*;
import services.DonationTypeService;
import viewmodels.DateConverter;

import java.util.*;

public class Global extends GlobalSettings {
  List<Map<String, String>> projects = new ArrayList<>();
  /*@Override
  public void onStart(Application app) {
    User testUser = createTestUser();
    Address address = createAddress();

    try {
      createTestProjects(address, testUser);
    } catch (AfricaException e) {
      e.printStackTrace();
    }
  }*/

  private static User createTestUser() {
    String mail = "test@test.ch";
    String password = "test";
    String hash = BCrypt.hashpw(password, BCrypt.gensalt());

    User testUser = new User();
    testUser.setEmail(mail);
    testUser.setFirstName("Max");
    testUser.setLastName("Muster");
    testUser.setPasswordHashedSalted(hash);
    testUser.save();
    return testUser;
  }

  private static void createTestProjects(Address address, User testUser) throws AfricaException {
    Map<String, String> hondurasMap = new HashMap<>();
    hondurasMap.put("title", "Handys nach Honduras");
    hondurasMap.put("description", "Honduras bracht Handys");
    hondurasMap.put("imageUrl", "http://images.nationalgeographic.com/wpf/media-live/photos/000/030/cache/honduras_3022_600x450.jpg");
    hondurasMap.put("startsAt", "2015-05-17");
    hondurasMap.put("endsAt", "2015-05-25");
    hondurasMap.put("contact", "Bitte per Email melden (honduras@honduras.com)");
    addTestProject(hondurasMap, address, testUser);

    Map<String, String> venezuelaMap = new HashMap<>();
    venezuelaMap.put("title", "Velos nach Venezuela");
    venezuelaMap.put("description", "Venezuela bracht Velos, damit sie wirder etwas fitter in den Beinen werden");
    venezuelaMap.put("imageUrl", "http://www.couch-mag.de/sites/default/files/styles/large/public/Artikelbild_4.jpg?itok=wgOi2O5f");
    venezuelaMap.put("startsAt", "2015-05-14");
    venezuelaMap.put("endsAt", "2015-05-22");
    venezuelaMap.put("contact", "Bitte per Email melden (velos@venelzuela.com)");
    addTestProject(venezuelaMap, address, testUser);

    Map<String, String> australienMap = new HashMap<>();
    australienMap.put("title", "Autos nach Australien");
    australienMap.put("description", "Die Australier sind viel zu sportlich, spendet Autos damit sie wieder dicker werden");
    australienMap.put("imageUrl", "http://www.in-australien.com/wp-content/uploads/2010/09/Campervan-Australien2.jpg");
    australienMap.put("startsAt", "2015-06-13");
    australienMap.put("endsAt", "2015-09-19");
    australienMap.put("contact", "Bitte per Email melden (autos@australien.com)");
    addTestProject(australienMap, address, testUser);

    Map<String, String> zimbabweMap = new HashMap<>();
    zimbabweMap.put("title", "Ziegel nach Zimbabwe");
    zimbabweMap.put("description", "Spendet Ziegel damit Zimbabwe bei Regen trocken schlafen kann");
    zimbabweMap.put("imageUrl", "http://www.planetstillalive.com/wp-content/uploads/2012/07/African-sunset1-1024x680.jpg");
    zimbabweMap.put("startsAt", "2015-07-09");
    zimbabweMap.put("endsAt", "2015-010-21");
    zimbabweMap.put("contact", "Bitte per Email melden (ziegel@zimbabwe.com)");
    addTestProject(zimbabweMap, address, testUser);

  }

  private static Project addTestProject(Map<String, String> projectData, Address address, User testUser) throws AfricaException {
    models.Project project = new Project();
    project.setTitle(projectData.get("title"));
    project.setDescription(projectData.get("description"));
    project.setImageURL(projectData.get("imageUrl"));
    project.setStartsAt(DateConverter.stringToSqlDate(projectData.get("startsAt")));
    project.setEndsAt(DateConverter.stringToSqlDate(projectData.get("endsAt")));
    project.setContact(projectData.get("contact"));
    project.setAddress(address);
    project.setOwner(testUser);
    project.save();
    addDonationGoals(project);
    return project;
  }

  private static void addDonationGoals(Project project) {
    List<String> donations = Arrays.asList("Laptop", "Hosen", "Schuhe");
    for (int i = 0; i < donations.size(); i++) {
      DonationType donationType = DonationTypeService.getOrSetDonationType(donations.get(i));
      DonationGoal donationGoal = new DonationGoal(project);
      donationGoal.setAmount(i+1);
      donationGoal.setType(donationType);
      donationGoal.setProject(project);
      donationGoal.save();
    }
  }

  private static Address createAddress() {
    Address address = new models.Address();
    address.setCountry("Honduras");
    address.setCity("Honduras");
    address.save();
    return address;
  }
  
  @Override
  public void onStop(Application app) {

  }  
    
}