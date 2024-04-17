package eu.miraiworks.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        Customer customer = Customer.builder()
                .firstname(customerRegistrationRequest.firstname())
                .lastname(customerRegistrationRequest.lastname())
                .email(customerRegistrationRequest.email())
                .build();

        //todo check if email is valid
        //todo check if email is taken
        customerRepository.save(customer);

    }

}
