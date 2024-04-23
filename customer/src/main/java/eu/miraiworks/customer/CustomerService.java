package eu.miraiworks.customer;

import eu.miraiworks.clients.fraud.FraudCheckResponse;
import eu.miraiworks.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        Customer customer = Customer.builder()
                .firstname(customerRegistrationRequest.firstname())
                .lastname(customerRegistrationRequest.lastname())
                .email(customerRegistrationRequest.email())
                .build();

        //todo check if email is valid
        //todo check if email is taken
        customerRepository.saveAndFlush(customer);
        //todo check if fraudster


        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse !=null && fraudCheckResponse.isFraudster()){
            System.out.println("Fraudster detected");
            throw new IllegalStateException("fraudster");
        }

        //todo send notification

    }

}
