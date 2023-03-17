package BLL.Validators;

import Model.Client;

import javax.swing.*;
import java.util.regex.Pattern;

public class NameValidator implements Validator<Client> {
    private static final String NAME_PATTERN = "^[\\p{L} .'-]+$";

    /**
     * this method validates client name
     * @param c client
     */
    public void validate(Client c) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(c.getName()).matches()) {
            JOptionPane.showMessageDialog(null, "Name is not valid");
            throw new IllegalArgumentException("Name is not a valid name!");
        }
    }

}
