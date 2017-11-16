package bufferstrategies;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.edoreld.elements.Page;
import com.edoreld.interfaces.BufferManagerInterface;

public class FirstInFirstOut implements BufferManagerInterface
{
	Page[]		pageArray;
	static int	pageFaults	= 0;

	public FirstInFirstOut() {
		pageArray = new Page[4];
	}

	@Override
	public void addPage(Page p) {
		System.out.println("Adding " + p.getPageName());
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}

		Objects.requireNonNull(p);

		int i = 0;

		while (i < pageArray.length) {
			// if the position is set to null, it means it's free and we can add
			// a page
			if (pageArray[i] == null) {
				pageArray[i] = p;
				return;
			}

			// If the page already exists in buffer manager, do nothing
			if (pageArray[i].equals(p)) {
				return;
			}
			i++;
		}

		FirstInFirstOut.pageFaults++;
		printArray();
		System.arraycopy(pageArray, 1, pageArray, 0, 3);
		pageArray[3] = p;
		printArray();

		// If we are here, it means that the array of frames is full, so we
		// simply replace
		// the least recent page with the new one
	}

	@Override
	public void printArray() {
		for (Page p : pageArray) {
			System.out.print(p.getPageName() + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {

		Page p1 = new Page("A");
		Page p2 = new Page("B");
		Page p3 = new Page("C");
		Page p4 = new Page("D");
		Page p5 = new Page("E");
		Page p6 = new Page("B");
		Page p7 = new Page("A");
		Page p8 = new Page("E");
		Page p9 = new Page("B");
		Page p10 = new Page("D");
		Page p11 = new Page("C");
		Page p12 = new Page("A");
		Page p13 = new Page("C");
		Page p14 = new Page("D");
		Page p15 = new Page("E");
		Page p16 = new Page("A");
		Page p17 = new Page("B");
		Page p18 = new Page("C");
		Page p19 = new Page("A");
		Page p20 = new Page("E");
		Page p21 = new Page("A");
		Page p22 = new Page("C");
		Page p23 = new Page("B");
		Page p24 = new Page("D");
		Page p25 = new Page("E");

		FirstInFirstOut manager = new FirstInFirstOut();

		manager.addPage(p1);
		manager.addPage(p2);
		manager.addPage(p3);
		manager.addPage(p4);
		manager.addPage(p5);
		manager.addPage(p6);
		manager.addPage(p7);
		manager.addPage(p8);
		manager.addPage(p9);
		manager.addPage(p10);
		manager.addPage(p11);
		manager.addPage(p12);
		manager.addPage(p13);
		manager.addPage(p14);
		manager.addPage(p15);
		manager.addPage(p16);
		manager.addPage(p17);
		manager.addPage(p18);
		manager.addPage(p19);
		manager.addPage(p20);
		manager.addPage(p21);
		manager.addPage(p22);
		manager.addPage(p23);
		manager.addPage(p24);
		manager.addPage(p25);

		manager.printArray();
		System.out.println(FirstInFirstOut.pageFaults);

	}
}
