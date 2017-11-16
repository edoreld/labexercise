package bufferstrategies;

import com.edoreld.elements.Page;
import com.edoreld.interfaces.BufferManagerInterface;

public class Clock implements BufferManagerInterface
{
	Page[]		pageArray;
	int[]		flags		= { -1, -1, -1, -1 };
	short		pointer;
	static int	pageFaults	= 0;

	public Clock() {
		pageArray = new Page[4];
		pointer = 0;
	}

	@Override
	public void addPage(Page p) {

		int i = 0;

		// First we check if the page to be inserted exists. If this is the
		// case, we simply set its flag to 1. Otherwise, if page is the same as
		// existing page, we
		// set the flag to 1.

		while (i < pageArray.length) {
			if (pageArray[i] == null) {
				pageArray[i] = p;
				flags[i] = 0;
				return;
			} else if (pageArray[i].equals(p)) {
				flags[i] = 1;
				return;
			}
			i++;
		}

		while (true) {
			if (pointer == pageArray.length) {
				pointer = 0;
			}
			if (flags[pointer] == 0) {
				Clock.pageFaults++;
				pageArray[pointer] = p;
				pointer++;

				break;
			} else if (flags[pointer] == 1) {
				flags[pointer] = 0;
				pointer++;
			}

		}
	}

	@Override
	public void printArray() {
		for (Page p : pageArray) {
			System.out.println(p.getPageName());
		}
	}

	public static void main(String[] args) {

		// Scenario where clock slower than LRU
		Page p1 = new Page("B");
		Page p2 = new Page("A");
		Page p3 = new Page("C");
		Page p4 = new Page("D");
		Page p5 = new Page("B");
		Page p6 = new Page("A");
		Page p7 = new Page("B");
		Page p8 = new Page("C");
		Page p9 = new Page("D");
		Page p10 = new Page("E");
		Page p11 = new Page("B");
		Page p12 = new Page("A");

		Clock manager = new Clock();

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

		manager.printArray();
		System.out.println(Clock.pageFaults);

	}
}
