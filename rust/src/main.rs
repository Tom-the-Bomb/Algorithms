
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

pub fn insertion_sort_nlogn<A, T>(arr: A) -> Vec<T>
where
    A: Into<Vec<T>>,
    T: PartialOrd + Copy,
{
    let mut arr = arr.into();

    for i in 0..arr.len() - 1 {
        let (mut left, mut right) = (0, i + 1);
        while (right - left) > 0 {
            let mid = (left + right) / 2;
            if arr[i + 1] > arr[mid] {
                left = mid + 1;
            } else if arr[i + 1] <= arr[mid] {
                right = mid;
            }
        }
        let elem = arr.remove(i + 1);
        arr.insert(left, elem);
    }
    arr
}

pub fn merge_sort<A, T>(arr: A) -> Vec<T>
where
    A: Into<Vec<T>>,
    T: PartialOrd + Copy,
{
    let arr = arr.into();

    if arr.len() == 1 {
        arr
    } else {
        let mid = arr.len() / 2;

        let mut left = merge_sort(arr[..mid].to_vec());
        let mut right = merge_sort(arr[mid..].to_vec());

        let mut new_arr = Vec::new();
        while !left.is_empty() && !right.is_empty() {
            let elem = if left.first()
                .map_or(false, |a| a < right.first().unwrap())
            {
                left.remove(0)
            } else {
                right.remove(0)
            };
            new_arr.push(elem);
        }
        left.extend(right);
        new_arr.extend(left);

        new_arr
    }
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

pub fn permutations<A, T>(
    arr: A, perms: Option<Vec<Vec<T>>>, i: Option<usize>
) -> Vec<Vec<T>>
where
    A: Into<Vec<T>>,
    T: Eq + Copy,
{
    let i = i.unwrap_or(0);
    let mut arr = arr.into();
    let mut _perms = perms
        .unwrap_or_else(|| vec![]);
    if arr.len() == i {
        _perms.push(arr);
        Default::default()
    } else {
        for j in i..arr.len() {
            if &arr[i] == &arr[j] {
                continue;
            }
            arr.swap(i, j);
            permutations(arr.clone(), Some(_perms.clone()), Some(i + 1));
            arr.swap(i, j);
        }
        _perms
    }
}

fn main() {
    let sample = [6, 3, 8, 1, 2, 10, 9, 4, 7, 5, 0];
    let output = (0..=10).collect::<Vec<_>>();

    assert_eq!(bubble_sort(sample), output);
    assert_eq!(selection_sort(sample), output);
    assert_eq!(insertion_sort(sample), output);
    assert_eq!(insertion_sort_nlogn(sample), output);
    assert_eq!(merge_sort(sample), output);
}