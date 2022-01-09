package hello.itemService.domain.item;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

	ItemRepository itemRepository = new ItemRepository();

	@AfterEach
	void afterEach() {
		itemRepository.clearStore();
	}

	@Test
	void save() {
		//given
		Item item = new Item("itemA", 10000, 10);
		//when
		Item save = itemRepository.save(item);
		//then
		Item findItem = itemRepository.findById(item.getId());
		assertThat(findItem).isEqualTo(save);
	}

	@Test
	void findAll() {
		//given
		Item itemA = new Item("itemA", 10000, 10);
		Item itemB = new Item("itemB", 10000, 20);
		itemRepository.save(itemA);
		itemRepository.save(itemB);

		//when
		List<Item> allItem = itemRepository.findAll();
		//then
		assertThat(allItem.size()).isEqualTo(2);
		assertThat(allItem).contains(itemA, itemB);
	}

	@Test
	void updateItem() {
		//given
		Item item = new Item("itemA", 10000, 10);
		Item savedItem = itemRepository.save(item);
		Long itemId = savedItem.getId();
		//when
		Item updateParam = new Item("item2", 20000, 30);
		itemRepository.update(itemId, updateParam);
		//then
		Item findItem = itemRepository.findById(itemId);

		assertThat(findItem.getId()).isEqualTo(updateParam.getId());
		assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
		assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
		assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
	}
}
