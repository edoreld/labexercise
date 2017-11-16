package bufferstrategies;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.edoreld.elements.Page;
import com.edoreld.interfaces.BufferManagerInterface;

public class LeastRecentlyUsed implements BufferManagerInterface
{
	Page[]		pageArray;
	long[]		recentness;
	static int	pageFaults	= 0;

	public LeastRecentlyUsed() {
		pageArray = new Page[4];
		recentness = new long[4];
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

		int indexOfLeastRecentPage = 0;
		long earliestDate = Long.MAX_VALUE;

		int i = 0;

		while (i < pageArray.length) {
			// if the position is set to null, it means it's free and we can add
			// a page
			if (pageArray[i] == null) {
				// p.setRecentnessToCurrentTime();
				recentness[i] = new Date().getTime();
				pageArray[i] = p;
				return;
			}

			// If the page already exists in disk, update its recentness to now
			if (pageArray[i].equals(p)) {
				recentness[i] = new Date().getTime();
				return;
			}

			// Every iteration of the loop we get the index of the page with the
			// least recent used date
			if (earliestDate > recentness[i]) {
				indexOfLeastRecentPage = i;
				earliestDate = recentness[i];
			}

			i++;
		}

		// If we are here, it means that the array of frames is full, so we
		// simply replace
		// the least recent page with the new one
		printArray();
		LeastRecentlyUsed.pageFaults++;
		recentness[indexOfLeastRecentPage] = new Date().getTime();
		pageArray[indexOfLeastRecentPage] = p;
		printArray();
	}

	@Override
	public void printArray() {
		for (Page p : pageArray) {
			System.out.print(p.getPageName() + " ");
		}
		System.out.println("");
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

		LeastRecentlyUsed manager = new LeastRecentlyUsed();

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
		System.out.println(LeastRecentlyUsed.pageFaults);

	}
}
