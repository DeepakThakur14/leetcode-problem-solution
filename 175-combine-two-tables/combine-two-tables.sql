# Write your MySQL query statement below
SELECT
    p.firstName,
    p.lastName,
    a.city,
    a.state
    From Person p
    Left join Address a
    ON p.personId = a.personId