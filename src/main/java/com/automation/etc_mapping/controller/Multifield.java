//package
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Controller
//public class Multifield {
//
//    @GetMapping("/")
//    public String showForm(Model model) {
//        MultiFieldForm multiFieldForm = new MultiFieldForm();
//        // Ensure at least one field pair is present
//        if (multiFieldForm.getSiteOtherLangs().isEmpty()) {
//            multiFieldForm.getSiteOtherLangs().add(new SiteOtherLang());
//        }
//        model.addAttribute("multiFieldForm", multiFieldForm);
//
//        // Provide the list of language codes
//        List<String> languageCodes = Arrays.asList("en", "fr", "es", "de", "it");
//        model.addAttribute("languageCodes", languageCodes);
//
//        return "form";
//    }
//
//    @PostMapping("/submit")
//    public String handleFormSubmit(@ModelAttribute MultiFieldForm multiFieldForm, Model model) {
//        // Process the siteOtherLangs multi-valued field
//        List<SiteOtherLang> siteOtherLangs = multiFieldForm.getSiteOtherLangs();
//        siteOtherLangs.removeIf(siteOtherLang ->
//                (siteOtherLang.getSiteOtherLang() == null || siteOtherLang.getSiteOtherLang().trim().isEmpty()) &&
//                        (siteOtherLang.getLangCode() == null || siteOtherLang.getLangCode().trim().isEmpty())
//        );
//
//        for (SiteOtherLang siteOtherLang : siteOtherLangs) {
//            System.out.println("Site's Other Language Path: " + siteOtherLang.getSiteOtherLang());
//            System.out.println("Site's Other Language Code: " + siteOtherLang.getLangCode());
//        }
//
//        // Add attributes to model
//        model.addAttribute("siteOtherLangs", siteOtherLangs);
//
//        return "result";
//    }
//}
