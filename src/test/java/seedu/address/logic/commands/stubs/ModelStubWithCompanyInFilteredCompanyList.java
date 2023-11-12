package seedu.address.logic.commands.stubs;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.company.Company;
import seedu.address.testutil.CompanyBuilder;

/**
 * A Model stub will be populated with exactly 1 company that is
 * a duplicate of the company being added, before the execution
 * of the add command. The duplicate company will be found in both the
 * filteredCompanyList and in the full company list. This simulates state
 * after any other command other than filter or find is executed.
 */
public class ModelStubWithCompanyInFilteredCompanyList extends ModelStub {

    public final ObservableList<Company> filteredCompanyList = FXCollections.observableArrayList();
    public final ObservableList<Company> fullCompanyList = FXCollections.observableArrayList();
    private final Company company;
    private Company lastViewedCompany;

    /**
     * Creates a ModelStub populated with a company that is a duplicate of the company being added.
     */
    public ModelStubWithCompanyInFilteredCompanyList(Company company, int... additionalCompanies) {
        requireNonNull(company);
        this.company = company;
        fullCompanyList.add(company);
        filteredCompanyList.add(company);
        CompanyBuilder cb = new CompanyBuilder(company);

        for (int i : additionalCompanies) {
            Company duplicateCompany = cb.withName("Company " + i).build();
            fullCompanyList.add(duplicateCompany);
            filteredCompanyList.add(duplicateCompany);
        }
    }

    @Override
    public boolean hasCompany(Company company) {
        requireNonNull(company);
        return this.company.isSameCompany(company);
    }

    @Override
    public Company getDuplicateCompany(Company otherCompany) {
        assert (otherCompany != null);

        if (otherCompany == this.company) {
            return this.company;
        }

        boolean condition = otherCompany.getName() != null
                && otherCompany.getName().equals(this.company.getName())
                && otherCompany.getRole() != null
                && otherCompany.getRole().equals(this.company.getRole())
                && otherCompany.getDeadline() != null
                && otherCompany.getDeadline().equals(this.company.getDeadline());

        return condition ? this.company : null;
    }

    @Override
    public void addCompany(Company company) {
        requireNonNull(company);
        fullCompanyList.add(company);
    }

    @Override
    public void setCurrentViewedCompany(Company company) {
        requireNonNull(company);
        lastViewedCompany = company;
    }

    @Override
    public int getDuplicateIndexFromOriginalAddressbook(Company company) {
        return fullCompanyList.indexOf(company);
    }

    @Override
    public int getDuplicateIndexFromFilteredAddressbook(Company company) {
        return filteredCompanyList.indexOf(company);
    }

    /**
     * Returns the last viewed company.
     */
    public Company getLastViewedCompany() {
        return lastViewedCompany;
    }
}