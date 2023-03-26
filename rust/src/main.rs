
pub fn selection_sort<A, T>(arr: A) -> Vec<T>
where
    A: Into<Vec<T>>,
    T: PartialOrd + Copy,
{
    let mut arr = arr.into();

    for n in (0..arr.len()).rev() {
        let mut maxi = 0;
        for i in 0..=n {
            if arr[i] > arr[maxi] {
                maxi = i;
            }
        }
        arr.swap(maxi, n);
    }
    arr
}

pub fn insertion_sort<A, T>(arr: A) -> Vec<T>
where
    A: Into<Vec<T>>,
    T: PartialOrd + Copy,
{
    let mut arr = arr.into();

    for i in 0..arr.len() - 1 {
        'found: {
            for j in (0..=i).rev() {
                if arr[i + 1] >= arr[j] {
                    let elem = arr.remove(i + 1);
                    arr.insert(j + 1, elem);
                    break 'found;
                }
            }
            let elem = arr.remove(i + 1);
            arr.insert(0, elem);
        }
    }
    arr
}

pub fn bubble_sort<A, T>(arr: A) -> Vec<T>
where
    A: Into<Vec<T>>,
    T: PartialOrd + Copy,
{
    let mut arr = arr.into();

    for t in 0..arr.len() {
        for i in 1..arr.len() - t {
            if arr[i - 1] > arr[i] {
                arr.swap(i - 1, i);
            }
        }
    }
    arr
}

fn main() {
    let sample = [6, 3, 8, 1, 2, 10, 9, 4, 7, 5, 0];
    let output = (0..=10).collect::<Vec<_>>();

    assert_eq!(bubble_sort(sample), output);
    assert_eq!(selection_sort(sample), output);
    assert_eq!(insertion_sort(sample), output);
}