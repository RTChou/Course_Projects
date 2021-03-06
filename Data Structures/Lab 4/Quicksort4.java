/*
 * Renee Ti Chou
 */
/**
 * <code>Quicksort4</code> selects the median of the first, middle, and last
 * elements of the partition as the pivot. Is has time complexity of O(n logn)
 * on average, and O(n^2) in the worst case, with ordered and reverse ordered
 * data.
 * <p>
 * The code except for <code>medianOfThree()</code>is borrowed from <a href=
 * "http://www.geeksforgeeks.org/iterative-quick-sort/">http://www.geeksforgeeks.org/iterative-quick-sort/</a>,
 * and is contributed by Rajat Mishra.
 */
public class Quicksort4 {

	/**
	 * Exchange the two elements in an array.
	 * 
	 * @param arr
	 *            the int array
	 * @param i
	 *            the index of the element
	 * @param j
	 *            the index of the other element
	 */
	private void swap(int arr[], int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	/**
	 * Select the median of three.
	 * 
	 * @param arr
	 *            the int array
	 * @param f
	 *            the index of the element
	 * @param m
	 *            the index of the element
	 * @param l
	 *            the index of the element
	 * @return the index of the median
	 */
	private int medianOfThree(int arr[], int f, int m, int l) {
		if (arr[m] >= arr[f] && arr[l] >= arr[m] || arr[m] >= arr[l] && arr[f] >= arr[m])
			return m;
		else if (arr[f] >= arr[m] && arr[l] >= arr[f] || arr[f] >= arr[l] && arr[m] >= arr[f])
			return f;
		else
			return l;
	}

	/**
	 * Select the first element as pivot and partition the array.
	 * 
	 * @param arr
	 *            the int array
	 * @param dn
	 *            the index of the first element of the partition
	 * @param up
	 *            the index of the last element of the partition
	 * @return the index of the pivot
	 */
	private int partition(int arr[], int dn, int up) {

		/* The additional pass to select the pivot */
		int m = medianOfThree(arr, dn, (dn + up) / 2, up);
		swap(arr, m, dn);

		int x = arr[dn]; // pivot
		int i = (up + 1);

		for (int j = up; j > dn; j--) {
			if (arr[j] >= x) {
				i--;
				swap(arr, i, j); // swap arr[i] and arr[j]
			}
		} // end for loop
		swap(arr, i - 1, dn); // swap arr[i+1] and arr[h]
		return (i - 1);
	}

	/**
	 * This method sorts the input array in ascending order.
	 * 
	 * @param arr
	 *            the int array to be sorted
	 * @return the elapsed time for sorting
	 */
	public long sort(int arr[]) {
		int n, top, h, l;
		int stack[];
		long time1, time2;

		n = arr.length;
		stack = new int[n]; // create auxiliary stack
		top = -1; // initialize top of stack

		time1 = System.nanoTime();

		if (n == 1) { // avoid stack overflow
			time2 = System.nanoTime();
			return (time2 - time1);
		}

		time1 = System.nanoTime();

		/* push initial values in the stack */
		stack[++top] = 0;
		stack[++top] = (n - 1);

		/* Do partition iteratively */
		while (top >= 0) {
			// pop h and l
			h = stack[top--];
			l = stack[top--];

			// set pivot element at it's proper position
			int p = partition(arr, l, h);

			// If there are elements on left side of pivot,
			// then push left side to stack
			if (p - 1 > l) {
				stack[++top] = l;
				stack[++top] = p - 1;
			}

			// If there are elements on right side of pivot,
			// then push right side to stack
			if (p + 1 < h) {
				stack[++top] = p + 1;
				stack[++top] = h;
			}
		} // end while
		time2 = System.nanoTime();
		return (time2 - time1);
	}
} // end class