package com.izeye.protobuf.tutorial;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link AddressBookProtos}.
 *
 * @author Johnny Lim
 */
class AddressBookProtosTests {

	@Test
	void test() throws InvalidProtocolBufferException {
		AddressBookProtos.AddressBook addressBook = AddressBookProtos.AddressBook.newBuilder()
				.addPeople(AddressBookProtos.Person.newBuilder()
						.setId(1)
						.setName("Johnny")
						.setEmail("johnny@izeye.com")
						.addPhones(AddressBookProtos.Person.PhoneNumber.newBuilder()
								.setNumber("1234-5678")
								.setType(AddressBookProtos.Person.PhoneType.HOME).build()))
				.build();
		byte[] bytes = addressBook.toByteArray();
		AddressBookProtos.AddressBook parsed = AddressBookProtos.AddressBook.parseFrom(bytes);
		assertThat(parsed.getPeople(0).getName()).isEqualTo(addressBook.getPeople(0).getName());
	}

}
