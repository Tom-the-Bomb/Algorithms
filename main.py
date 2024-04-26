import random
import itertools

def binary_search(arr: list[int], target: int) -> bool:
    middle = arr[len(arr) // 2]

    if len(arr) == 1 and arr[0] != target:
        return False

    if target == middle:
        return True
    elif target > middle:
        return binary_search(arr[len(arr) // 2:], target)
    else:
        return binary_search(arr[:len(arr) // 2], target)

def looped_binary_search(arr: list[int], target: int) -> bool:
    left, right = 0, len(arr) - 1

    while left <= right:
        middle = (left + right) // 2

        if target > arr[middle]:
            left = middle + 1
        elif target < arr[middle]:
            right = middle - 1
        else:
            return True
    return False

def bubble_sort(arr: list[int]) -> list[int]:
    for t in range(len(arr)):
        for i in range(1, len(arr) - t):
            if arr[i - 1] > arr[i]:
                arr[i - 1], arr[i] = arr[i], arr[i - 1]
    return arr

def selection_sort(arr: list[int]) -> list[int]:
    for n in range(len(arr) - 1, -1, -1):
        maxi = 0
        for i in range(n + 1):
            if arr[i] > arr[maxi]:
                maxi = i
        arr[n], arr[maxi] = arr[maxi], arr[n]
    return arr

def insertion_sort(arr: list[int]) -> list[int]:
    for i in range(len(arr) - 1):
        for j in range(i, -1, -1):
            if arr[i + 1] >= arr[j]:
                arr.insert(j + 1, arr.pop(i + 1))
                break
        else:
            arr.insert(0, arr.pop(i + 1))
    return arr

def insertion_sort_swap(arr: list[int]) -> list[int]:
    for i in range(len(arr) - 1):
        for j in range(i, -1, -1):
            if arr[j + 1] >= arr[j]:
                break
            arr[j], arr[j + 1] = arr[j + 1], arr[j]
    return arr

def insertion_sort_binary_search(arr: list[int]) -> list[int]:
    for i in range(len(arr) - 1):
        left, right = 0, i + 1
        while (right - left) > 0:
            mid = (left + right) // 2
            if arr[i + 1] > arr[mid]:
                left = mid + 1
            elif arr[i + 1] <= arr[mid]:
                right = mid
        arr.insert(left, arr.pop(i + 1))
    return arr

def merge_sort(arr: list[int]) -> list[int]:
    if len(arr) == 1:
        return arr
    mid = len(arr) // 2

    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])

    new_arr = []
    while left and right:
        if left[0] < right[0]:
            new_arr.append(left.pop(0))
        else:
            new_arr.append(right.pop(0))
    new_arr += left + right
    return new_arr

def permutations(nums: list[int], perms: set[tuple[int]] = None, i: int = 0) -> set[tuple[int]]:
    if perms is None:
        perms = set()

    if len(nums) == i:
        return perms.add(tuple(nums))

    for j in range(i, len(nums)):
        if nums[i] == nums[j] and i != j:
            continue
        nums[i], nums[j] = nums[j], nums[i]
        permutations(nums, perms, i + 1)
        nums[i], nums[j] = nums[j], nums[i]
    return perms

if __name__ == '__main__':
    sample = list(range(100))

    assert binary_search(sample, 5)
    assert not binary_search(sample, 101)

    assert looped_binary_search(sample, 5)
    assert not looped_binary_search(sample, 101)

    random.shuffle(sample)
    assert bubble_sort(sample) == list(range(100))
    random.shuffle(sample)
    assert selection_sort(sample) == list(range(100))
    random.shuffle(sample)
    assert insertion_sort(sample) == list(range(100))
    random.shuffle(sample)
    assert insertion_sort_swap(sample) == list(range(100))
    random.shuffle(sample)
    assert insertion_sort_binary_search(sample) == list(range(100))
    random.shuffle(sample)
    assert merge_sort(sample) == list(range(100))

    assert permutations([1, 1, 3]) == set(itertools.permutations([1, 1, 3]))