package org.example.server.server.interfa;



import org.example.pojo.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> list(Address address);

    void save(Address address);

    Address getById(Long id);

    void update(Address address);

    void setDefault(Address address);

    void deleteById(Long id);

}
