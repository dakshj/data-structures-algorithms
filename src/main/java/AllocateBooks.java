import java.util.Collections;
import java.util.List;

class AllocateBooks {

    public int allocateBooks(final List<Integer> pages, final int students) {
        if (students == 0 || pages.size() < students) {
            return -1;
        }

        int startPages = 0;
        int sumPages = pages.stream().mapToInt(Integer::intValue).sum();
        final int maxPagesInABook = Collections.max(pages);

        int result = Integer.MAX_VALUE;

        while (startPages <= sumPages) {
            final int mid = startPages + (sumPages - startPages) / 2;

            if (mid >= maxPagesInABook && isPossible(mid, pages, students)) {
                result = Math.min(result, mid);

                // Search the left half in the next iteration
                sumPages = mid - 1;
            } else {
                // Search the right half in the next iteration
                startPages = mid + 1;
            }
        }

        return result;
    }

    /**
     * Returns whether or not it is possible to distribute the total no. of pages in all books
     * amongst the no. of students, with each student getting <= mid amount of pages.
     */
    private boolean isPossible(final int mid, final List<Integer> pages, final int students) {
        int studentCount = 1;
        int currentSum = 0;

        for (Integer currPages : pages) {
            // This means that mid is too small, and thus the total no. of pages cannot be
            // distributed correctly.
            if (currPages > mid) {
                return false;
            }

            // If the sum up till the current book exceeds the "mid" amount of pages to be
            // distributed, then we need to allocate these pages to another student.
            if (currentSum + currPages > mid) {
                studentCount++;
                // Thus, reset currentSum (which will now be used for tracking
                // the assigned no. of pages to the next student.
                currentSum = currPages;

                // Don't wait till the end of all the iterations to check if the available no.
                // of students is less than the students required to distribute the total
                // no. of pages, with each student getting "mid" no. of pages.
                if (studentCount > students) {
                    return false;
                }
            }
            // If the sum is still under the "mid" amount of pages to be distributed,
            // we continue assigning pages of more books to the same student.
            else {
                // Assign more pages to the same student
                currentSum = currentSum + currPages;
            }
        }

        // We were able to distribute at most "mid" no. of pages (out of the total no. of pages)
        // to the students (some students may have not gotten any pages, which is okay).
        return true;
    }
}
